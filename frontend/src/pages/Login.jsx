import "../styles/login.css";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import logo from "../assets/logo.png"
import { Link } from "react-router-dom";

export default function Login({ isClient }) {
  const navigate = useNavigate();
  const [loginData, setLoginData] = useState({
    username: "",
    password: "",
  });

  const handleFormEdit = (event, name) => {
    setLoginData({
      ...loginData,
      [name]: event.target.value,
    });
  };

  const handleForm = async (event) => {
    let route = `http://localhost:8080/clients/login`;
    if (!isClient) route = `http://localhost:8080/barbers/login`;
    try {
      event.preventDefault();
      const response = await fetch(route, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(loginData),
      });
      if (response.status === 200) {
        localStorage.setItem("user", JSON.stringify(await response.json()));
        if (isClient) {
          navigate("/clientDashboard");
        } else {
          navigate("/barberDashboard");
        }
      }else{
        alert('Usuário ou senha inválidos')
      }
    } catch (err) {}
  };

  return (
    <div className="loginForm">
      <form onSubmit={handleForm} className="formDiv">
        <Link to="/">
          <img src={logo} style={{width:'100%'}}/>
        </Link>
        <input
          className="inputGroup"
          type="text"
          placeholder="Digite o seu username"
          required
          value={loginData.username}
          onChange={(e) => {
            handleFormEdit(e, "username");
          }}
        />
        <input
          className="inputGroup"
          type="password"
          placeholder="Digite sua senha"
          required
          value={loginData.password}
          onChange={(e) => {
            handleFormEdit(e, "password");
          }}
        />
        <button type="submit" style={{width:'100%', color:'#21201F',background:'white',marginTop:'15px'}}>Fazer login</button>
      </form>
    </div>
  );
}
