import './App.css';
import {ThemeProvider} from '@mui/material';
import greenTheme from './theme/greenTheme';
import SalonDetails from './Salon/Salon Details/SalonDetails';

function App() {
    return (
        <ThemeProvider theme={greenTheme}>
            {/* <Home/> */}
            <SalonDetails/>
        </ThemeProvider>

    );
}

export default App;
