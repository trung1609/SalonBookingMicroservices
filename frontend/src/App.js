import './App.css';
import {ThemeProvider} from '@mui/material';
import greenTheme from './theme/greenTheme';
import {Route} from "react-router-dom";
import SalonDashboard from "./Salon/SalonDashboard";
import {Routes} from "react-router-dom";
import CustomerRoutes from "./Routes/CustomerRoutes";
function App() {
    return (
        <ThemeProvider theme={greenTheme}>
            <Routes>
                <Route path={"/salon-dashboard/*"} element={<SalonDashboard/>}/>
                <Route path={"*"} element={<CustomerRoutes/>}/>
            </Routes>
        </ThemeProvider>

    );
}

export default App;
