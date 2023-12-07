import "../styles/barberDashboard.css";
import { useEffect, useState } from "react";
import AppointmentsClients from "../components/AppointmentsClients";
import BarberCard from "../components/BarberCard";
import AppointmentCard from "../components/AppointmentCard";
import { DemoContainer } from "@mui/x-date-pickers/internals/demo";
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";
import { LocalizationProvider } from "@mui/x-date-pickers/LocalizationProvider";
import { DateTimePicker } from "@mui/x-date-pickers/DateTimePicker";
import dayjs from "dayjs";

export default function BarberDashboard() {
  const [user, setUser] = useState({});
  const [allAppointments, setAllAppointments] = useState([]);
  const [reload, setReload] = useState(false);

  const getAllAppointments = async () => {
    try {
      const response = await fetch(
        `http://localhost:8080/appointments/all/barber/${
          JSON.parse(localStorage.getItem("user")).id
        }`,
        {
          method: "GET",
        }
      );
      setAllAppointments(await response.json());
    } catch (err) {}
  };

  useEffect(() => {
    getAllAppointments();
    let userParser = localStorage.getItem("user");
    setUser(JSON.parse(userParser));
  }, []);
  useEffect(() => {
    getAllAppointments();
  }, [reload]);

  return (
    <section
      style={{
        width: "100vw",
        display: "flex",
        justifyContent: "center",
        flexDirection: "column",
        alignItems: "center",
      }}
    >
      <h1
        style={{ fontSize: "30px", fontStyle: "italic", marginBottom: "30px" }}
      >
        Oi {user.username}, esses são seus agendamentos:
      </h1>
      <div
        className="appointmentsClients"
        style={{
          marginBottom: "15px",
          display: "flex",
          flexDirection: "column",
          gap: "7px",
        }}
      >
        {allAppointments.map((value) => {
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
            <AppointmentsClients
              username={value.client.username}
              day={formattedToday}
              hour={today.toLocaleTimeString("pt-BR", {
                timeZone: "America/Recife",
                hour: "2-digit",
                minute: "2-digit",
              })}
              state={value.enumState}
              id={value.id}
              reload={reload}
              setReload={setReload}
            />
          );
        })}
      </div>
    </section>
  );
}
