import React, { useRef, useState } from "react";
import InputField from "../components/InputField";
import { FaUser, FaLock } from "react-icons/fa";
import { MdEmail } from "react-icons/md";
import axios from "axios";
import { useDispatch } from "react-redux";
import { useNavigate } from "react-router-dom";
import { toast } from "react-toastify";

const Register = () => {
  const [email, setEmail] = useState("");
  const [name, setName] = useState("");
  const [password, setPassword] = useState("");
  const [image, setImage] = useState("");
  const [imageUrl, setImageUrl] = useState("");
  const navigate = useNavigate();

  const uploadFileRef = useRef();

  const handleImageUpload = (e) => {
    const file = e.target.files;
    if (file.length == 0) {
      setImage("");
      setImageUrl("");
    } else {
      setImage(e.target.files[0]);
      setImageUrl(URL.createObjectURL(e.target.files[0]));
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (imageUrl === "") {
      toast.error("upload profile image");
    } else if (email === "" || password === "" || name === "") {
      toast.error("Name, email or password cannot be empty");
    } else {
      const formData = new FormData();

      formData.append("name", name);
      formData.append("password", password);
      formData.append("email", email);
      formData.append("image", image);

      axios
        .post("http://localhost:8080/api/auth/register", formData, {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        })
        .then((resp) => {
          navigate("/login");
          toast.success("User registered");
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
      <div className="p-10 bg-orange-200 rounded-xl shadow-lg">
        <form
          className="flex flex-col items-center gap-y-6"
          onSubmit={handleSubmit}
        >
          <button
            onClick={(e) => {
              e.preventDefault();
              uploadFileRef.current.click();
            }}
            className="h-20 w-20  rounded-full object-contain overflow-hidden bg-gray-500"
          >
            {imageUrl === "" ? (
              <div className="text-white">Profile Image</div>
            ) : (
              <img src={imageUrl} className="w-full h-full object-cover" />
            )}
          </button>
          <input
            ref={uploadFileRef}
            type="file"
            accept=".jpg, .jpeg, .png"
            onChange={handleImageUpload}
            hidden
          />

          <InputField
            icon={<FaUser />}
            type="text"
            label="full name"
            value={name}
            setValue={setName}
          />
          <InputField
            type="email"
            label="email"
            icon={<MdEmail />}
            value={email}
            setValue={setEmail}
          />
          <InputField
            type="password"
            label="password"
            icon={<FaLock />}
            value={password}
            setValue={setPassword}
          />
          <div className="flex justify-center">
            <button
              type="submit"
              className="bg-blue-950 text-gray-200 rounded px-2 py-1 duration-400"
            >
              Register
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default Register;
