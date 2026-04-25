import logo from './logo.svg';
import './App.css';
import { Button, ThemeProvider } from '@mui/material';
import greenTheme from './theme/greenTheme';
import Home from './Customer/Home/Home';

function App() {
  return (
    <ThemeProvider theme={greenTheme}>
      <Home/>
    </ThemeProvider>

  );
}

export default App;
