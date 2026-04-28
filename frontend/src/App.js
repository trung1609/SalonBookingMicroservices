import './App.css';
import {ThemeProvider} from '@mui/material';
import greenTheme from './theme/greenTheme';
import Notifications from "./Customer/Notification/Notifications";

function App() {
    return (
        <ThemeProvider theme={greenTheme}>
            {/* <Home/> */}
            {/*<SalonDetails/>*/}
            {/*<Bookings/>*/}
            <Notifications/>
        </ThemeProvider>

    );
}

export default App;
