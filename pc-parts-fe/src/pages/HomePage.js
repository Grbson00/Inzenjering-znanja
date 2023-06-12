import { Box, Typography, useTheme } from '@mui/material';
import FunctionalityCard from '../components/home/FunctionalityCard';

const calculateCardPosition = (index, totalCards) => {
    const angle = (index / totalCards) * 360 + 18;
    const radius = 250;

    const centerX = 0;
    const centerY = 0;

    const offsetX = Math.cos(angle * (Math.PI / 180)) * radius;
    const offsetY = Math.sin(angle * (Math.PI / 180)) * radius;

    const x = centerX + offsetX;
    const y = centerY + offsetY;

    return { x, y };
};
const cardStyles = {
    position: 'absolute',
    margin: '2rem 0',
    boxShadow: 'rgba(0, 0, 0, 0.35) 0px 5px 15px'
};

const HomePage = () => {
    const cardCount = 5;
    const cards = [];
    const cardData = [
        { title: 'Find a Part for my PC', image: 'part.jpg', link: '/find' },
        { title: 'Find Upgrade for my PC', image: 'upgrade.webp', link: 'upgrade' },
        { title: 'Best Usage for my PC', image: 'pc.webp', link: '/evaluate' },
        { title: "What's causing this Error?", image: 'crash.png', link: '/cause' },
        { title: 'Find Similar PC by Specs', image: 'cpu.jpg', link: '/similar' },
    ]

    for (let i = 0; i < cardCount; i++) {
        const { x, y } = calculateCardPosition(i, cardCount);
        const cardStyle = {
            ...cardStyles,
            transform: `translate(${x}px, ${y}px)`,
        };

        cards.push(
            <div key={i} style={cardStyle}>
                <FunctionalityCard image={cardData[i].image} title={cardData[i].title} route={cardData[i].link} />
            </div>
        );
    }

    const theme = useTheme()

    return (
        <>
            <Box sx={{
                position: 'absolute', top: '39%', left: '43%',
                backgroundColor: theme.palette.primary.main, padding: '2rem',
                borderRadius: '500px', color: "white", boxShadow: 'rgba(0, 0, 0, 0.35) 0px 5px 15px'
            }}>
                <Typography textAlign={"center"} variant="h3">
                    Build
                </Typography>
                <Typography textAlign={"center"} variant="h3">
                    Master
                </Typography>
            </Box>
            <Box sx={{ display: 'grid', placeItems: 'center', height: 'calc(100vh - 2rem)' }}>
                {cards}
            </Box>
        </>
    );
};

export default HomePage;
