import "../styles/appointmentCard.css";

export default function AppointmentCard(props) {
  return (
    <div className="sectionAppointment">
      <div
        className="appointmentCard"
        style={{
          backgroundColor:
            props.state === "PENDING"
              ? "#868e96"
              : props.state === "CANCELED"
              ? "#f03e3e"
              : "#69db7c",
        }}
      >
        <h1>Barbeiro: {props.username}</h1>
        <h1>Dia: {props.day}</h1>
        <h1>Hora: {props.hour}</h1>
        <h1>Status: {props.state}</h1>
      </div>
    </div>
  );
}
