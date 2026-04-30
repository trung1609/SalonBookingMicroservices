import './App.css';
import {ThemeProvider} from '@mui/material';
import greenTheme from './theme/greenTheme';
import {Route, Routes} from "react-router-dom";
import SalonDashboard from "./Salon/SalonDashboard";
import CustomerRoutes from "./Routes/CustomerRoutes";
import SignupForm from "./Auth/SignupForm";
import LoginForm from "./Auth/LoginForm";
import Auth from "./Auth/Auth";

function App() {
    return (
        <ThemeProvider theme={greenTheme}>
            <Routes>
                <Route path={"/salon-dashboard/*"} element={<SalonDashboard/>}/>
                <Route path={"/register"} element={<Auth/>}/>
                <Route path={"/login"} element={<Auth/>}/>
                <Route path={"*"} element={<CustomerRoutes/>}/>
            </Routes>
        </ThemeProvider>

    );
}

export default App;
