import { Box, Stack } from '@mui/material';
import {
    Chart as ChartJS,
    CategoryScale,
    LinearScale,
    BarElement,
    Title,
    Tooltip,
    Legend,
} from 'chart.js';
import { useEffect } from 'react';
import { Bar } from 'react-chartjs-2';

ChartJS.register(
    CategoryScale,
    LinearScale,
    BarElement,
    Title,
    Tooltip,
    Legend
);

export const options = {
    responsive: true,
    plugins: {
        legend: {
            position: 'top',
        },
        title: {
            display: true,
            text: 'Chart.js Bar Chart',
        },
    },
};

const labels = ['Development', 'Gaming', 'Crypto mining', 'Home PC', 'Work PC', 'Hosting PC'];


const ResultChart = ({ chartData }) => {
    const data = {
        labels,
        datasets: [
            {
                label: 'PC Utility',
                data: [chartData.development, chartData.gaming, chartData.crypto, chartData.home, chartData.work, chartData.hosting],
                backgroundColor: 'rgba(52, 152, 219, 0.5)',
            }
        ],
    };

    const findMaxProperties = (data) => {
        let maxProperties = [];
        let maxValue = Number.NEGATIVE_INFINITY;
      
        for (const property in data) {
          if (data.hasOwnProperty(property)) {
            const value = data[property];
            if (value > maxValue) {
              maxValue = value;
              maxProperties = [property];
            } else if (value >= maxValue - 3 && value <= maxValue + 3) {
              maxProperties.push(property);
            }
          }
        }
      
        return maxProperties;
      };

    return (
        <Stack direction={"row"} spacing={4} sx={{ width: '100%' }}>
            <Box sx={{ width: '50%' }}>
                <Bar options={options} data={data} />
            </Box>
            <Box sx={{ width: '50%' }}>
                This PC would be best to be used for: {findMaxProperties(chartData).join(', ')}
            </Box>
        </Stack>
    );
}

export default ResultChart;