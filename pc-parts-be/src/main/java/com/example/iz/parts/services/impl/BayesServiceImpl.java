package com.example.iz.parts.services.impl;

import com.example.iz.parts.dto.bayes.BayesDTO;
import com.example.iz.parts.dto.bayes.BayesReturnDTO;
import com.example.iz.parts.services.BayesService;
import org.springframework.stereotype.Service;
import unbbayes.io.BaseIO;
import unbbayes.io.NetIO;
import unbbayes.prs.Node;
import unbbayes.prs.bn.JunctionTreeAlgorithm;
import unbbayes.prs.bn.ProbabilisticNetwork;
import unbbayes.prs.bn.ProbabilisticNode;
import unbbayes.util.extension.bn.inference.IInferenceAlgorithm;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BayesServiceImpl implements BayesService {

    @Override
    public ArrayList<BayesReturnDTO> GetErrorCause(BayesDTO dto) throws IOException {

         ProbabilisticNetwork net;
         BaseIO io = new NetIO();
         net = (ProbabilisticNetwork) io.load(new File("data/bayes.net"));

        IInferenceAlgorithm algorithm = new JunctionTreeAlgorithm();
        algorithm.setNetwork(net);
        algorithm.run();

        //koliko sam ja razumeo ovde fecujemo sve simptome koji su poslati i stavljamo ih na 1, moguce da ne treba ovaj for loop
        for(String symptom : dto.getSymptomList()) {
            ProbabilisticNode factNode = (ProbabilisticNode)net.getNode(symptom.replace(" ", "_"));
            int stateIndex = 0;
            factNode.addFinding(stateIndex);
        }

        try {
            net.updateEvidences();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        List<Node> nodeList = net.getNodes();
        ArrayList<BayesReturnDTO> retList = new ArrayList<>();

        for (Node node : nodeList) {
            System.out.println(node.getName());
            for (int i = 0; i < node.getStatesSize(); i++) {
                //retList.add(new BayesReturnDTO(node.getStateAt(i), ((ProbabilisticNode)node).getMarginalAt(i)));
                System.out.println(node.getStateAt(i) + " : " + ((ProbabilisticNode)node).getMarginalAt(i));
                if(node.getStateAt(i).equals("yes") && node.getName().endsWith("error")) {
                    retList.add(new BayesReturnDTO(node.getName().replace("_", " ") ,((ProbabilisticNode) node).getMarginalAt(i)*100));
                }
            }
        }



        List<BayesReturnDTO> topFiveObjects = retList.stream()
                .sorted(Comparator.comparingDouble(BayesReturnDTO::getPercentage).reversed())
                .limit(5)
                .collect(Collectors.toList());

        System.out.println(topFiveObjects);
        ArrayList<BayesReturnDTO> arrayList = new ArrayList<>(topFiveObjects);
        return arrayList;
    }

    @Override
    public ArrayList<String> GetProblemCause() throws IOException {

        ProbabilisticNetwork net;
        BaseIO io = new NetIO();
        net = (ProbabilisticNetwork) io.load(new File("data/bayes.net"));


        List<Node> nodeList = net.getNodes();
        ArrayList<String> retList = new ArrayList<>();

        for (Node node : nodeList) {
            System.out.println(node.getName());
            if (node.getName().endsWith("problem")){
                retList.add(node.getName().replace("_", " "));
            }
        }


        System.out.println(retList);

        return retList;
    }
}
