import { Route, Routes } from "react-router";
import { BrowserRouter } from "react-router-dom";
import { ThemeProvider } from "@mui/material";
import './App.css'
import FindComponentPage from "./pages/FindComponentPage";
import HomePage from "./pages/HomePage";
import RecommendUpgradePage from "./pages/RecommendUpgradePage";
import Layout from "./components/common/Layout";
import theme from "./theme";

function App() {
  return (
    // <ThemeProvider theme={theme}>
        <BrowserRouter>
          <Layout>
            <Routes>
                  <Route path={'/'} element={<HomePage />} />
                  <Route path={'/find'} element={<FindComponentPage />} />
                  <Route path={'/upgrade'} element={<RecommendUpgradePage />} />
            </Routes>
          </Layout>
        </BrowserRouter>
    // </ThemeProvider>
  );
}

export default App;
