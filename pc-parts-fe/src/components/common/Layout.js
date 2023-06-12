import { useEffect, useState } from 'react';
import { useLocation } from 'react-router-dom';

const Layout = ({ children }) => {
    const location = useLocation();
    const [locationState, setLocationState] = useState()

    useEffect(() => {
        setLocationState(location.pathname)
    }, [location])

    return (
        <>
            <main style={{ padding: (locationState === "/") ? "0rem" : "2rem 5rem" }}>
                {children}
            </main>
        </>
    );
}

export default Layout;