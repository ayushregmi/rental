import { Route, Routes } from "react-router-dom";
import Login from "./ui/elements/Login";
import NavBar from "./ui/elements/NavBar";
import Register from "./ui/elements/Register";
import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

function App() {
  return (
    <>
      <div className="relative w-screen relative flex flex-col h-screen">
        <ToastContainer
          autoClose={1000}
          pauseOnHover={false}
          closeOnClick={true}
        />
        <NavBar />
        <div className="flex-grow bg-orange-100"></div>
        <Routes>
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Register />} />
        </Routes>
      </div>
    </>
  );
}

export default App;
