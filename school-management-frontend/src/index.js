import React, { useState } from "react";
import ReactDOM from "react-dom";
import "./index.css";
import School from "./components/School/School";
import Employee from "./components/Employee/Employee";
import Student from "./components/Student/Student";
import reportWebVitals from "./reportWebVitals";

function App() {
  const [currentPage, setCurrentPage] = useState("school");

  const handlePageChange = (page) => {
    setCurrentPage(page);
  };

  let currentPageComponent;

  if (currentPage === "school") {
    currentPageComponent = <School />;
  } else if (currentPage === "employee") {
    currentPageComponent = <Employee />;
  } else if (currentPage === "student") {
    currentPageComponent = <Student />;
  }

  return (
    <div className="mt-5">
      <div className="ms-8 relative inline-block w-48">
        <select
          className=" block appearance-none w-full bg-white border border-gray-300  px-4 py-2 pr-8 rounded-lg shadow leading-tight focus:outline-none focus:shadow-outline"
          value={currentPage}
          onChange={(e) => handlePageChange(e.target.value)}
        >
          <option value="school">School</option>
          <option value="employee">Employee</option>
          <option value="student">Student</option>
        </select>
        <div className="pointer-events-none absolute inset-y-0 right-0 flex items-center pr-2 text-gray-700">
          <svg
            className="fill-current h-4 w-4"
            xmlns="http://www.w3.org/2000/svg"
            viewBox="0 0 20 20"
          >
            <path fillRule="evenodd" d="M10 14l6-6H4l6 6z" />
          </svg>
        </div>
      </div>
      {currentPageComponent}
    </div>
  );
}

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>
);

reportWebVitals();
