import "../styles/clientDashboard.css";
import { useEffect, useState } from "react";
import BarberCard from "../components/BarberCard";
import AppointmentCard from "../components/AppointmentCard";
import { DemoContainer } from "@mui/x-date-pickers/internals/demo";
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";
import { LocalizationProvider } from "@mui/x-date-pickers/LocalizationProvider";
import { DateTimePicker } from "@mui/x-date-pickers/DateTimePicker";
import dayjs from "dayjs";
import logo from "../assets/logo.png";

export default function ClientDashboard() {
  const [user, setUser] = useState({});
  const [allBarbers, setAllBarbers] = useState([]);
  const [allAppointments, setAllAppointments] = useState([]);
  const [time, setTime] = useState(dayjs());
  const [dataPickerDisable, setDataPickerDisable] = useState(true);
  const [dataAppointment, setDataAppointment] = useState({
    barberUsername: "",
    clientUsername: "",
    appointmentDateTime: "",
  });

  const getAllAppointments = async () => {
    try {
      const response = await fetch(
        `http://localhost:8080/appointments/all/client/${
          JSON.parse(localStorage.getItem("user")).id
        }`,
        {
          method: "GET",
        }
      );
      setAllAppointments(await response.json());
    } catch (err) {}
  };

  const getAllBarbers = async () => {
    try {
      const response = await fetch(`http://localhost:8080/barbers/all`, {
        method: "GET",
      });
      setAllBarbers(await response.json());
    } catch (err) {}
  };

  const createAppointment = async () => {
    try {
      const response = await fetch(`http://localhost:8080/appointments`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(dataAppointment),
      });
      getAllAppointments();
      setDataPickerDisable(true);
      setDataAppointment({
        barberUsername: "",
        clientUsername: "",
        appointmentDateTime: "",
      });
    } catch (err) {}
  };

  useEffect(() => {
    getAllBarbers();
    getAllAppointments();
    let userParser = localStorage.getItem("user");
    setUser(JSON.parse(userParser));
  }, []);

  useEffect(() => {
    setDataAppointment({
      ...dataAppointment,
      appointmentDateTime: time,
    });
  }, [time]);

  return (
    <section className="clientDashSection">
      <img src={logo} style={{ width: "40%" }} />
      <div className="clientDashboard">
        <h1
          style={{
            fontSize: "23px",
            fontStyle: "italic",
            marginBottom: "10px",
          }}
        >
          Oi <span style={{ color: "yellow" }}>{user.username}</span>, quem será
          o barbeiro da vez?
        </h1>
        <h3 style={{ fontSize: "15px" }}>(Seus agendamentos atuais:)</h3>
        <div
          className="appointmentsContainer"
          style={{
            marginBottom: "100px",
            display: "flex",
            flexDirection: "column",
            gap: "7px",
          }}
        >
          {allAppointments.map((value) => {
            // console.log(value);

            const today = new Date(value.appointmentDateTime);
            const yyyy = today.getFullYear();
            let mm = today.getMonth() + 1;
            let dd = today.getDate();

            if (dd < 10) dd = "0" + dd;
            if (mm < 10) mm = "0" + mm;

            const formattedToday = dd + "/" + mm + "/" + yyyy;
            let teste = today;
            teste.setHours(teste.getHours() - 3);
            return (
              <AppointmentCard
                username={value.barber.username}
                day={formattedToday}
                hour={today.toLocaleTimeString("pt-BR", {
                  timeZone: "America/Recife",
                  hour: "2-digit",
                  minute: "2-digit",
                })}
                state={value.enumState}
              />
            );
          })}
        </div>
        <h1 style={{ fontSize: "23px", fontWeight: "bold" }}>
          Barbeiros Disponíveis:
        </h1>

        <div className="barbersContainer">
          {allBarbers.map((value) => {
            return (
              <BarberCard
                name={value.username}
                email={value.email}
                selected={dataAppointment.barberUsername === value.username}
                onClick={() => {
                  setDataAppointment({
                    ...dataAppointment,
                    barberUsername: value.username,
                    clientUsername: user.username,
                  });
                  setDataPickerDisable(false);
                }}
              />
            );
          })}
        </div>
      </div>

      <div
        style={{
          display: "flex",
          flexDirection: "column",
          gap: "20px ",
          marginTop: "50px",
        }}
      >
        <div
          style={{
            backgroundColor: "white",
            padding: "10px 20px",
            borderRadius: "10px",
          }}
        >
          <LocalizationProvider dateAdapter={AdapterDayjs}>
            <DemoContainer components={["DateTimePicker"]}>
              <DateTimePicker
                style={{ color: "white" }}
                label="Seleciona o dia e a hora"
                value={time}
                onChange={(newValue) => {
                  setTime(newValue);
                }}
                disabled={dataPickerDisable}
              />
            </DemoContainer>
          </LocalizationProvider>
        </div>

        <button
          type="submit"
          disabled={dataPickerDisable}
          onClick={createAppointment}
          style={{ background: "#94d82d", color: "#21201F" }}
        >
          Registrar
        </button>
      </div>
    </section>
  );
}
