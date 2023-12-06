import "../styles/login.css";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

export default function Login() {
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
    try {
      event.preventDefault();
      const response = await fetch(`http://localhost:8080/clients/login`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(loginData),
      });
      if (response.status === 200) {
        localStorage.setItem("user", JSON.stringify(await response.json()));
        navigate("/clientDashboard");
        // console.log(await response.json());
      }
    } catch (err) {}
  };

  return (
    <div>
      <form onSubmit={handleForm} className="formDiv">
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
        <button type="submit">Fazer login</button>
      </form>
    </div>
  );
}
