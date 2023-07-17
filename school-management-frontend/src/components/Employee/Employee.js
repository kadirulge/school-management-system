import React, { useState, useEffect } from "react";

function Employee() {
  const [employees, setEmployees] = useState([]);
  const [editMode, setEditMode] = useState(false);
  const [editedEmployee, setEditedEmployee] = useState({});
  const [newEmployee, setNewEmployee] = useState({
    firstName: "",
    lastName: "",
    address: "",
    phoneNumber: "",
    employeeNumber: "",
    manager: false,
    jobTitle: "",
    schoolId: "",
  });

  const jobTitles = [
    "TEACHER",
    "PRINCIPAL",
    "VICE_PRINCIPAL",
    "JANITOR",
    "COOK",
    "SECRETARY",
    "NURSE",
    "LIBRARIAN",
    "COUNSELOR",
    "ADMINISTRATOR",
    "BUS_DRIVER",
  ];

  useEffect(() => {
    fetchEmployees();
  }, [editMode]);

  const fetchEmployees = async () => {
    try {
      const response = await fetch("/api/employees");
      const data = await response.json();
      setEmployees(data);
    } catch (error) {
      console.error("Error:", error);
    }
  };

  const handleEdit = (employee) => {
    setEditMode(true);
    setEditedEmployee(employee);
  };

  const handleCancel = () => {
    setEditMode(false);
    setEditedEmployee({});
  };

  const handleSave = async () => {
    try {
      const response = await fetch(`/api/employees/${editedEmployee.id}`, {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(editedEmployee),
      });

      if (response.ok) {
        console.log("Updated:", editedEmployee);
        setEditMode(false);
        setEditedEmployee({});
      } else {
        console.error("Update failed");
      }
    } catch (error) {
      console.error("Error:", error);
    }
  };

  const handleDelete = async (id) => {
    try {
      const response = await fetch(`/api/employees/${id}`, {
        method: "DELETE",
      });

      if (response.ok) {
        console.log("Deleted:", id);
        fetchEmployees();
      } else {
        console.error("Delete failed");
      }
    } catch (error) {
      console.error("Error:", error);
    }
  };

  const handleCreate = async () => {
    try {
      const response = await fetch("/api/employees", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(newEmployee),
      });

      if (response.ok) {
        const createdEmployee = await response.json();
        setEmployees([...employees, createdEmployee]);
        console.log("Created:", createdEmployee);
        setNewEmployee({
          firstName: "",
          lastName: "",
          address: "",
          phoneNumber: "",
          employeeNumber: "",
          manager: false,
          jobTitle: "",
          schoolId: "",
        });
      } else {
        console.error("Create failed");
      }
    } catch (error) {
      console.error("Error:", error);
    }
  };

  return (
    <div className="flex flex-col">
      <div className="-my-2 -px-2">
        <div className="py-2 align-middle inline-block min-w-full sm:px-6 lg:px-8">
          <div className="mt-2 shadow overflow-hidden border-b border-gray-200 sm:rounded-lg">
            <table className="min-w-full divide-y divide-gray-200">
              <thead className="bg-gray-50">
                <tr>
                  <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                    Employee Id
                  </th>
                  <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                    Employee First Name
                  </th>
                  <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                    Employee Last Name
                  </th>
                  <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                    Employee Address
                  </th>
                  <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                    Employee Phone Number
                  </th>
                  <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                    Employee Number
                  </th>
                  <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                    Is Manager
                  </th>
                  <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                    Job Title
                  </th>
                  <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                    School Id
                  </th>
                  <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                    Actions
                  </th>
                </tr>
              </thead>
              <tbody className="bg-white divide-y divide-gray-200">
                {employees.map((employee) => (
                  <tr key={employee.id}>
                    <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                      {employee.id}
                    </td>
                    <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                      {editMode && editedEmployee.id === employee.id ? (
                        <input
                          type="text"
                          className="px-2 py-1 border rounded-lg"
                          value={editedEmployee.firstName || ""}
                          onChange={(e) =>
                            setEditedEmployee({
                              ...editedEmployee,
                              firstName: e.target.value,
                            })
                          }
                        />
                      ) : (
                        employee.firstName
                      )}
                    </td>
                    <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                      {editMode && editedEmployee.id === employee.id ? (
                        <input
                          type="text"
                          className="px-2 py-1 border rounded-lg"
                          value={editedEmployee.lastName || ""}
                          onChange={(e) =>
                            setEditedEmployee({
                              ...editedEmployee,
                              lastName: e.target.value,
                            })
                          }
                        />
                      ) : (
                        employee.lastName
                      )}
                    </td>
                    <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                      {editMode && editedEmployee.id === employee.id ? (
                        <input
                          type="text"
                          className="px-2 py-1 border rounded-lg"
                          value={editedEmployee.address || ""}
                          onChange={(e) =>
                            setEditedEmployee({
                              ...editedEmployee,
                              address: e.target.value,
                            })
                          }
                        />
                      ) : (
                        employee.address
                      )}
                    </td>
                    <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                      {editMode && editedEmployee.id === employee.id ? (
                        <input
                          type="text"
                          className="px-2 py-1 border rounded-lg"
                          value={editedEmployee.phoneNumber || ""}
                          onChange={(e) =>
                            setEditedEmployee({
                              ...editedEmployee,
                              phoneNumber: e.target.value,
                            })
                          }
                        />
                      ) : (
                        employee.phoneNumber
                      )}
                    </td>
                    <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                      {editMode && editedEmployee.id === employee.id ? (
                        <input
                          type="text"
                          className="px-2 py-1 border rounded-lg"
                          value={editedEmployee.employeeNumber || ""}
                          onChange={(e) =>
                            setEditedEmployee({
                              ...editedEmployee,
                              employeeNumber: e.target.value,
                            })
                          }
                        />
                      ) : (
                        employee.employeeNumber
                      )}
                    </td>
                    <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                      {editMode && editedEmployee.id === employee.id ? (
                        <input
                          type="checkbox"
                          checked={editedEmployee.manager}
                          onChange={(e) =>
                            setEditedEmployee({
                              ...editedEmployee,
                              manager: e.target.checked,
                            })
                          }
                        />
                      ) : employee.manager ? (
                        "Yes"
                      ) : (
                        "No"
                      )}
                    </td>
                    <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                      {editMode && editedEmployee.id === employee.id ? (
                        <select
                          className="px-2 py-1 border rounded-lg"
                          value={editedEmployee.jobTitle || ""}
                          onChange={(e) =>
                            setEditedEmployee({
                              ...editedEmployee,
                              jobTitle: e.target.value,
                            })
                          }
                        >
                          <option value="">Select Job Title</option>
                          {jobTitles.map((jobTitle) => (
                            <option key={jobTitle} value={jobTitle}>
                              {jobTitle}
                            </option>
                          ))}
                        </select>
                      ) : (
                        employee.jobTitle
                      )}
                    </td>
                    <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                      {employee.schoolId}
                    </td>
                    <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                      {editMode && editedEmployee.id === employee.id ? (
                        <>
                          <button
                            className="mr-2 text-blue-500"
                            onClick={handleSave}
                          >
                            Save
                          </button>
                          <button
                            className="text-red-500"
                            onClick={handleCancel}
                          >
                            Cancel
                          </button>
                        </>
                      ) : (
                        <button
                          className="text-blue-500"
                          onClick={() => handleEdit(employee)}
                        >
                          Edit
                        </button>
                      )}
                      {!editMode && (
                        <button
                          className="text-red-500 ms-3"
                          onClick={() => handleDelete(employee.id)}
                        >
                          Delete
                        </button>
                      )}
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        </div>
      </div>
      <div className="my-4 ms-8">
        <h2 className="text-gray-800 text-lg font-semibold text-gray mb-1">
          Create New Employee
        </h2>
        <div className="flex">
          <input
            type="text"
            className="px-2 py-1 border rounded-lg mr-2"
            placeholder="First Name"
            value={newEmployee.firstName}
            onChange={(e) =>
              setNewEmployee({ ...newEmployee, firstName: e.target.value })
            }
          />
          <input
            type="text"
            className="px-2 py-1 border rounded-lg mr-2"
            placeholder="Last Name"
            value={newEmployee.lastName}
            onChange={(e) =>
              setNewEmployee({ ...newEmployee, lastName: e.target.value })
            }
          />
          <input
            type="text"
            className="px-2 py-1 border rounded-lg mr-2"
            placeholder="Address"
            value={newEmployee.address}
            onChange={(e) =>
              setNewEmployee({ ...newEmployee, address: e.target.value })
            }
          />
          <input
            type="text"
            className="px-2 py-1 border rounded-lg mr-2"
            placeholder="Phone Number"
            value={newEmployee.phoneNumber}
            onChange={(e) =>
              setNewEmployee({ ...newEmployee, phoneNumber: e.target.value })
            }
          />
          <input
            type="text"
            className="px-2 py-1 border rounded-lg mr-2"
            placeholder="Employee Number"
            value={newEmployee.employeeNumber}
            onChange={(e) =>
              setNewEmployee({ ...newEmployee, employeeNumber: e.target.value })
            }
          />
          <select
            className="px-2 py-1 border rounded-lg mr-2"
            value={newEmployee.jobTitle}
            onChange={(e) =>
              setNewEmployee({ ...newEmployee, jobTitle: e.target.value })
            }
          >
            <option value="">Select Job Title</option>
            {jobTitles.map((jobTitle) => (
              <option key={jobTitle} value={jobTitle}>
                {jobTitle}
              </option>
            ))}
          </select>
          <input
            type="text"
            className="px-2 py-1 border rounded-lg mr-2"
            placeholder="School Id"
            value={newEmployee.schoolId}
            onChange={(e) =>
              setNewEmployee({ ...newEmployee, schoolId: e.target.value })
            }
          />
          <label className="mt-2 mr-2">Is Manager</label>
          <input
            type="checkbox"
            className="mr-2"
            checked={newEmployee.manager}
            onChange={(e) =>
              setNewEmployee({ ...newEmployee, manager: e.target.checked })
            }
          />
          <button
            className="px-4 py-2 bg-green-500 text-white rounded-lg"
            onClick={handleCreate}
          >
            Create
          </button>
        </div>
      </div>
    </div>
  );
}

export default Employee;
