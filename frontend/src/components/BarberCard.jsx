import "../styles/barberCard.css";

export default function BarberCard(props) {
  return (
    <div
      className="barberCard"
      {...props}
      style={props.selected ? { backgroundColor: "black" } : {}}
    >
      <h1 style={{ whiteSpace: "nowrap" }}>Nome: {props.name}</h1>
      <h1 style={{ whiteSpace: "nowrap" }}>Email: {props.email}</h1>
    </div>
  );
}
