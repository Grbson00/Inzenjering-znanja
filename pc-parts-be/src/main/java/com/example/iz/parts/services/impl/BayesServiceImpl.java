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
import java.util.List;

@Service
public class BayesServiceImpl implements BayesService {

    @Override
    public ArrayList<BayesReturnDTO> GetErrorCause(BayesDTO dto) throws IOException {

         ProbabilisticNetwork net;
         BaseIO io = new NetIO();
         net = (ProbabilisticNetwork)io.load(new File("data/bayes.net"));

        IInferenceAlgorithm algorithm = new JunctionTreeAlgorithm();
        algorithm.setNetwork(net);
        algorithm.run();

        //koliko sam ja razumeo ovde fecujemo sve simptome koji su poslati i stavljamo ih na 1, moguce da ne treba ovaj for loop
        for(String symptom : dto.getSymptomList()) {
            ProbabilisticNode factNode = (ProbabilisticNode)net.getNode(symptom);
            int stateIndex = 1;
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
                retList.add(new BayesReturnDTO(node.getStateAt(i), ((ProbabilisticNode)node).getMarginalAt(i)));
                System.out.println(node.getStateAt(i) + ": " + ((ProbabilisticNode)node).getMarginalAt(i));
            }
        }

        System.out.println(retList);

        return retList;
    }
}
