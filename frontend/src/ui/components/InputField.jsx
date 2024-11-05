import React, { useState } from "react";

const InputField = ({ type = "", label = "", value, setValue, icon }) => {
  const [focused, setFocused] = useState(false);

  return (
    <div className="flex flex-row bg-white rounded-lg gap-1 items-center">
      {icon && <div className="flex items-center pl-1">{icon ? icon : ""}</div>}
      <div className="relative flex-grow mx-2">
        <div className="absolute inset-0 pointer-events-none flex items-center">
          <label
            className={`origin-top-left transition-all duration-400 text-gray-400 font-semibold ${
              focused | (value !== "")
                ? "scale-75 -translate-y-4 -translate-x-1"
                : ""
            }`}
          >
            {label}
          </label>
        </div>

        <input
          value={value}
          onChange={(e) => setValue(e.target.value)}
          onFocus={() => setFocused(true)}
          onBlur={() => setFocused(false)}
          className="w-full outline-none h-8 bg-transparent"
          type={type}
        />
      </div>
    </div>
  );
};

export default InputField;
