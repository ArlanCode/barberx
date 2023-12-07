import { useState } from "react";
import "../styles/register.css";
import { useNavigate } from "react-router-dom";
import logo from "../assets/logo.png"
import { Link } from "react-router-dom";

export default function Register() {
  const navigate = useNavigate();
  const [typeRegister, setTypeRegister] = useState("client");
  const [registerData, setRegisterData] = useState({
    username: "",
    fullName: "",
    email: "",
    password: "",
  });

  const handleFormEdit = (event, name) => {
    setRegisterData({
      ...registerData,
      [name]: event.target.value,
    });
  };

  const handleForm = async (event) => {
    let route = `http://localhost:8080/clients/`;

    if (typeRegister === "barber") {
      route = `http://localhost:8080/barbers/`;
    }

    try {
      event.preventDefault();
      const response = await fetch(route, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(registerData),
      });
      alert('Usuario cadastrado')
      if(typeRegister === 'barber'){
        navigate("/login/barber")
      }else{
        navigate("/login/client")
      }
    } catch (err) {}
  };

  return (
    <section className="registerForm">
      
      <form onSubmit={handleForm} className="formDiv">
        <Link to="/">
          <img src={logo} style={{width:'100%'}}/>
        </Link>  
        <input
          className="inputGroup"
          type="text"
          placeholder="Username"
          required
          value={registerData.username}
          onChange={(e) => {
            handleFormEdit(e, "username");
          }}
        />
        <input
          className="inputGroup"
          type="text"
          placeholder="Nome completo"
          required
          value={registerData.fullName}
          onChange={(e) => {
            handleFormEdit(e, "fullName");
          }}
        />
        <input
          className="inputGroup"
          type="text"
          placeholder="Email"
          required
          value={registerData.email}
          onChange={(e) => {
            handleFormEdit(e, "email");
          }}
        />

        <input
          className="inputGroup"
          type="password"
          placeholder="Senha"
          required
          value={registerData.password}
          onChange={(e) => {
            handleFormEdit(e, "password");
          }}
        />

        <select
          onChange={(e) => {
            setTypeRegister(e.target.value);
          }}
          style={{marginBottom:'20px'}}
        >
          <option value="client">Cadastrar como cliente</option>
          <option value="barber">Cadastrar como barbeiro</option>
        </select>
        <button type="submit" style={{width:'100%'}}>Registrar</button>
      </form>
    </section>
  );
}
