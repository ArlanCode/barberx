import { useEffect, useState } from "react";

export default function AppointmentsClients(props) {
  const changeState = async (state) => {
    alert("Estado do agendamento sendo alterado!");
    if (state) {
      await fetch(`http://localhost:8080/appointments/${props.id}/accept`, {
        method: "PUT",
      });
    } else {
      await fetch(`http://localhost:8080/appointments/${props.id}/cancel`, {
        method: "PUT",
      });
    }
    props.setReload(!props.reload);
  };

  return (
    <div
      style={{
        width: "50vw",
        borderRadius: "10px",
        maxWidth: "1000px",
        backgroundColor:
          props.state === "PENDING"
            ? "gray"
            : props.state === "CANCELED"
            ? "red"
            : "green",
      }}
    >
      <div
        style={{
          display: "flex",
          fontSize: "5px",
          justifyContent: "space-between",
          color: "white",
          padding: "2px 14px",
        }}
      >
        <h1>Cliente: {props.username}</h1>
        <h1>Dia: {props.day}</h1>
        <h1>Hora: {props.hour}</h1>
        <h1>Status: {props.state}</h1>

        {props.state === "PENDING" && (
          <button
            onClick={() => changeState(true)}
            style={{ padding: "0", margin: "0", fontSize: "10px" }}
          >
            Aceitar
          </button>
        )}
        {props.state !== "CANCELED" && (
          <button
            onClick={() => changeState(false)}
            style={{
              padding: "0",
              margin: "0",
              fontSize: "10px",
              backgroundColor: "red",
              color: "white",
            }}
          >
            Recusar
          </button>
        )}
      </div>
    </div>
  );
}
