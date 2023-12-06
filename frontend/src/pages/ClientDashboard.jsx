import "../styles/clientDashboard.css";
import { useEffect, useState } from "react";
import BarberCard from "../components/BarberCard";
import AppointmentCard from "../components/AppointmentCard";
import { DemoContainer } from "@mui/x-date-pickers/internals/demo";
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";
import { LocalizationProvider } from "@mui/x-date-pickers/LocalizationProvider";
import { DateTimePicker } from "@mui/x-date-pickers/DateTimePicker";
import dayjs from "dayjs";

export default function ClientDashboard() {
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
      const response = await fetch(`http://localhost:8080/appointments/all`, {
        method: "GET",
      });
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
  }, []);

  useEffect(() => {
    setDataAppointment({
      ...dataAppointment,
      appointmentDateTime: time,
    });
  }, [time]);

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
      <div className="clientDashboard">
        <h1>Olá novamente!</h1>
        <h3>(Seus agendamentos:)</h3>
        <div
          className="appointmentsContainer"
          style={{
            marginBottom: "15px",
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
        <h3>-------------</h3>
        <h1>Barbeiros Disponíveis:</h1>

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
                    clientUsername: "joao",
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
        <LocalizationProvider dateAdapter={AdapterDayjs}>
          <DemoContainer components={["DateTimePicker"]}>
            <DateTimePicker
              label="Seleciona o dia e a hora"
              value={time}
              onChange={(newValue) => {
                setTime(newValue);
              }}
              disabled={dataPickerDisable}
            />
          </DemoContainer>
        </LocalizationProvider>
        <button
          type="submit"
          disabled={dataPickerDisable}
          onClick={createAppointment}
        >
          Registrar
        </button>
      </div>
    </section>
  );
}
