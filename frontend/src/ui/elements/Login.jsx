import React, { useState } from "react";
import InputField from "../components/InputField";
import { FaUser, FaLock } from "react-icons/fa";
import axios from "axios";
import { useDispatch } from "react-redux";
import { login } from "../../redux/user/UserSlice";
import { useNavigate } from "react-router-dom";
import { toast } from "react-toastify";

const Login = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (email === "" || password === "") {
      toast.error("Email and password cannot be empty");
    } else {
      axios
        .post("http://localhost:8080/api/auth/login", { email, password })
        .then((resp) => {
          console.log(resp.data);
          dispatch(login(resp.data));
          navigate("/home");
          toast.success("Logged In");
        })
        .catch((err) => {
          if (err.code === "ERR_NETWORK") {
            toast.error(err.message);
          } else {
            toast.error(err.data.message);
          }
        });
    }
  };

  return (
    <div className="h-full w-full flex items-center justify-center bg-orange-100">
      <div className="p-10 bg-orange-200 rounded-xl shadow-xl">
        <form className="flex flex-col gap-y-5" onSubmit={handleSubmit}>
          <InputField
            type="email"
            label="Email"
            icon={<FaUser />}
            value={email}
            setValue={setEmail}
          />
          <InputField
            type="password"
            label="Password"
            value={password}
            icon={<FaLock />}
            setValue={setPassword}
          />
          <div className="flex justify-center">
            <button
              type="submit"
              className="bg-blue-950 text-gray-200 rounded px-2 py-1 duration-400"
            >
              Login
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default Login;
