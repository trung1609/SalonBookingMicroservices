import './App.css';
import {ThemeProvider} from '@mui/material';
import greenTheme from './theme/greenTheme';
import Notifications from "./Customer/Notification/Notifications";
import Navbar from "./Customer/Navbar/Navbar";
import Home from "./Customer/Home/Home";
import Bookings from "./Customer/Booking/Bookings";
import SalonDetails from "./Salon/Salon Details/SalonDetail";

function App() {
    return (
        <ThemeProvider theme={greenTheme}>
            <Navbar/>
             <Home/>
            {/*<SalonDetails/>*/}
            {/*<Bookings/>*/}
            {/*<Notifications/>*/}
        </ThemeProvider>

    );
}

export default App;
