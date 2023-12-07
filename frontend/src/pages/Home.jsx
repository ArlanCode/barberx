import Button from "react-bootstrap/Button";
import "../styles/home.css";
import { Link } from "react-router-dom";

export default function Home() {
  return (
    <container className="buttonsOption">
      <div className="buttonsDiv">
        <div style={{ display: "flex", gap: "20px" }}>
          <Link to="login/client">
            <Button variant="primary" size="lg" style={{ width: "100%" }}>
              Login Cliente
            </Button>
          </Link>
          <Link to="login/barber">
            <Button variant="primary" size="lg" style={{ width: "100%" }}>
              Login Barbeiro
            </Button>
          </Link>
        </div>

        <Link to="register">
          <Button variant="primary" size="lg" style={{ width: "100%" }}>
            Registrar-se
          </Button>
        </Link>
      </div>
    </container>
  );
}
