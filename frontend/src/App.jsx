import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Home from "./pages/Home";
import Register from "./pages/Register";
import Login from "./pages/Login";
import ClientDashboard from "./pages/ClientDashboard";
import BarberDashboard from "./pages/BarberDashboard";
import "bootstrap/dist/css/bootstrap.min.css";

export default function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="register" element={<Register />} />
        <Route path="clientDashboard" element={<ClientDashboard />} />
        <Route path="login/client" element={<Login isClient={true} />} />
        <Route path="login/barber" element={<Login isClient={false} />} />
        <Route path="barberDashboard" element={<BarberDashboard />} />
      </Routes>
    </Router>
  );
}
