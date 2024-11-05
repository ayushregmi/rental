import React from "react";
import { useDispatch, useSelector } from "react-redux";
import { Link, useLocation } from "react-router-dom";
import { logout } from "../../redux/user/UserSlice";

const NavBar = () => {
  const location = useLocation();
  const { user, loggedIn } = useSelector((state) => state.user);
  const dispatch = useDispatch();

  const handleLogout = () => {
    dispatch(logout());
  };

  return (
    <div className="sticky top-0 left-0 w-full bg-[#001F3F] px-10 flex flex-row justify-between items-center text-white py-4">
      <div className="flex flex-row gap-12 items-center">
        <div className="text-4xl">Random Logo</div>
        <div className="flex flex-row gap-8 mt-2">
          <Link
            to="/home"
            className={`${
              location.pathname.toLowerCase() === "/home" ? "border-b" : ""
            }`}
          >
            Home
          </Link>
          <Link
            to="/vehicles"
            className={`${
              location.pathname.toLowerCase() === "/vehicles" ? "border-b" : ""
            }`}
          >
            Vehicles
          </Link>
          {loggedIn && (
            <Link
              to="/myrentals"
              className={`${
                location.pathname.toLowerCase() === "/myrentals"
                  ? "border-b"
                  : ""
              }`}
            >
              My Rentals
            </Link>
          )}
        </div>
      </div>
      {loggedIn ? (
        <div className="flex flex-row items-center gap-x-10">
          <div className="mt-2 flex flex-row gap-x-8">
            <div className="">Profile</div>
            <button onClick={handleLogout} className="">
              Logout
            </button>
          </div>

          <img
            src={user.profileUrl}
            className="h-10 w-10 rounded-full object-cover"
          />
        </div>
      ) : (
        <div className="flex flex-row gap-4">
          <Link to="/login" className="border py-1 px-2 rounded-xl">
            Login
          </Link>
          <Link
            to="/register"
            className="border bg-white py-1 px-2 rounded-xl text-[#001F3F]"
          >
            Register
          </Link>
        </div>
      )}
    </div>
  );
};

export default NavBar;
