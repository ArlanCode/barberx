import "../styles/barberCard.css";

export default function BarberCard(props) {
  return (
    <div
      className="barberCard"
      {...props}
      style={
        props.selected ? { backgroundColor: "#94d82d", color: "black" } : {}
      }
    >
      <span class="material-symbols-outlined">cut</span>
      <div>
        <h1 style={{ whiteSpace: "nowrap", fontSize: "13px" }}>
          Nome: {props.name}
        </h1>
        <h1 style={{ whiteSpace: "nowrap", fontSize: "13px" }}>
          Email: {props.email}
        </h1>
      </div>
    </div>
  );
}
