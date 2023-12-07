import Button from "react-bootstrap/Button";
import "../styles/home.css";
import { Link } from "react-router-dom";
import logo from "../assets/logo.png"

export default function Home() {
  return (
    <section className="buttonsSection">
      <div style={{width:'350px'}}>
      <img src={logo} style={{width:'100%'}}/>
        <div className="buttonsDiv"> 
          <div style={{ display: "flex", gap: "20px" , justifyContent:'space-between'}}>
            <Link to="login/client">
              <Button variant="primary" size="lg" style={{ width: "100%", backgroundColor:'white', color:'#21201F', border:'none', fontSize:'20px'}} className="buttonOptions">
                Login Cliente
              </Button>
            </Link>
            <Link to="login/barber">
              <Button variant="primary" size="lg" style={{ width: "100%", backgroundColor:'white', color:'#21201F', border:'none'}}>
                Login Barbeiro
              </Button>
            </Link>
          </div>

          <Link to="register">
            <Button variant="primary" size="lg" style={{ width: "100%", backgroundColor:'white', color:'#21201F', border:'none'}}>
              Registrar-se
            </Button>
          </Link>
        </div>
      </div>
    </section>
  );
}
