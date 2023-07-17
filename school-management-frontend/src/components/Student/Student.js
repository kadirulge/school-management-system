import React, { useState, useEffect } from "react";

function Student() {
  const [students, setStudents] = useState([]);
  const [editMode, setEditMode] = useState(false);
  const [editedStudent, setEditedStudent] = useState({});
  const [newStudent, setNewStudent] = useState({
    firstName: "",
    lastName: "",
    email: "",
    address: "",
    phoneNumber: "",
    studentNumber: "",
    schoolId: "",
  });

  useEffect(() => {
    fetchStudents();
  }, [editMode]);

  const fetchStudents = async () => {
    try {
      const response = await fetch("/api/students");
      const data = await response.json();
      setStudents(data);
    } catch (error) {
      console.error("Error:", error);
    }
  };

  const handleEdit = (student) => {
    setEditMode(true);
    setEditedStudent(student);
  };

  const handleCancel = () => {
    setEditMode(false);
    setEditedStudent({});
  };

  const handleSave = async () => {
    try {
      const response = await fetch(`/api/students/${editedStudent.id}`, {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(editedStudent),
      });

      if (response.ok) {
        console.log("Updated:", editedStudent);
        setEditMode(false);
        setEditedStudent({});
      } else {
        console.error("Update failed");
      }
    } catch (error) {
      console.error("Error:", error);
    }
  };

  const handleDelete = async (id) => {
    try {
      const response = await fetch(`/api/students/${id}`, {
        method: "DELETE",
      });

      if (response.ok) {
        console.log("Deleted:", id);
        fetchStudents();
      } else {
        console.error("Delete failed");
      }
    } catch (error) {
      console.error("Error:", error);
    }
  };

  const handleCreate = async () => {
    try {
      const response = await fetch("/api/students", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(newStudent),
      });

      if (response.ok) {
        const createdStudent = await response.json();
        setStudents([...students, createdStudent]);
        console.log("Created:", createdStudent);
        setNewStudent({
          firstName: "",
          lastName: "",
          email: "",
          address: "",
          phoneNumber: "",
          studentNumber: "",
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
                    Student Id
                  </th>
                  <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                    Student First Name
                  </th>
                  <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                    Student Last Name
                  </th>
                  <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                    Student Email
                  </th>
                  <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                    Student Address
                  </th>
                  <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                    Student Phone Number
                  </th>
                  <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                    Student Number
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
                {students.map((student) => (
                  <tr key={student.id}>
                    <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                      {student.id}
                    </td>
                    <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                      {editMode && editedStudent.id === student.id ? (
                        <input
                          type="text"
                          className="px-2 py-1 border rounded-lg"
                          value={editedStudent.firstName || ""}
                          onChange={(e) =>
                            setEditedStudent({
                              ...editedStudent,
                              firstName: e.target.value,
                            })
                          }
                        />
                      ) : (
                        student.firstName
                      )}
                    </td>
                    <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                      {editMode && editedStudent.id === student.id ? (
                        <input
                          type="text"
                          className="px-2 py-1 border rounded-lg"
                          value={editedStudent.lastName || ""}
                          onChange={(e) =>
                            setEditedStudent({
                              ...editedStudent,
                              lastName: e.target.value,
                            })
                          }
                        />
                      ) : (
                        student.lastName
                      )}
                    </td>
                    <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                      {editMode && editedStudent.id === student.id ? (
                        <input
                          type="text"
                          className="px-2 py-1 border rounded-lg"
                          value={editedStudent.email || ""}
                          onChange={(e) =>
                            setEditedStudent({
                              ...editedStudent,
                              email: e.target.value,
                            })
                          }
                        />
                      ) : (
                        student.email
                      )}
                    </td>
                    <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                      {editMode && editedStudent.id === student.id ? (
                        <input
                          type="text"
                          className="px-2 py-1 border rounded-lg"
                          value={editedStudent.address || ""}
                          onChange={(e) =>
                            setEditedStudent({
                              ...editedStudent,
                              address: e.target.value,
                            })
                          }
                        />
                      ) : (
                        student.address
                      )}
                    </td>
                    <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                      {editMode && editedStudent.id === student.id ? (
                        <input
                          type="text"
                          className="px-2 py-1 border rounded-lg"
                          value={editedStudent.phoneNumber || ""}
                          onChange={(e) =>
                            setEditedStudent({
                              ...editedStudent,
                              phoneNumber: e.target.value,
                            })
                          }
                        />
                      ) : (
                        student.phoneNumber
                      )}
                    </td>
                    <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                      {editMode && editedStudent.id === student.id ? (
                        <input
                          type="text"
                          className="px-2 py-1 border rounded-lg"
                          value={editedStudent.studentNumber || ""}
                          onChange={(e) =>
                            setEditedStudent({
                              ...editedStudent,
                              studentNumber: e.target.value,
                            })
                          }
                        />
                      ) : (
                        student.studentNumber
                      )}
                    </td>
                    <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                      {editMode && editedStudent.id === student.id ? (
                        <input
                          type="text"
                          className="px-2 py-1 border rounded-lg"
                          value={editedStudent.schoolId || ""}
                          onChange={(e) =>
                            setEditedStudent({
                              ...editedStudent,
                              schoolId: e.target.value,
                            })
                          }
                        />
                      ) : (
                        student.schoolId
                      )}
                    </td>
                    <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                      {editMode && editedStudent.id === student.id ? (
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
                        <>
                          <button
                            className="text-blue-500"
                            onClick={() => handleEdit(student)}
                          >
                            Edit
                          </button>
                          <button
                            className="text-red-500 ms-3"
                            onClick={() => handleDelete(student.id)}
                          >
                            Delete
                          </button>
                        </>
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
          Create New Student
        </h2>
        <div className="flex mt-2">
          <input
            type="text"
            placeholder="First Name"
            className="px-2 py-1 border rounded-lg mr-2"
            value={newStudent.firstName}
            onChange={(e) =>
              setNewStudent({ ...newStudent, firstName: e.target.value })
            }
          />
          <input
            type="text"
            placeholder="Last Name"
            className="px-2 py-1 border rounded-lg mr-2"
            value={newStudent.lastName}
            onChange={(e) =>
              setNewStudent({ ...newStudent, lastName: e.target.value })
            }
          />
          <input
            type="text"
            placeholder="Email"
            className="px-2 py-1 border rounded-lg mr-2"
            value={newStudent.email}
            onChange={(e) =>
              setNewStudent({ ...newStudent, email: e.target.value })
            }
          />
          <input
            type="text"
            placeholder="Address"
            className="px-2 py-1 border rounded-lg mr-2"
            value={newStudent.address}
            onChange={(e) =>
              setNewStudent({ ...newStudent, address: e.target.value })
            }
          />
          <input
            type="text"
            placeholder="Phone Number"
            className="px-2 py-1 border rounded-lg mr-2"
            value={newStudent.phoneNumber}
            onChange={(e) =>
              setNewStudent({ ...newStudent, phoneNumber: e.target.value })
            }
          />
          <input
            type="text"
            placeholder="Student Number"
            className="px-2 py-1 border rounded-lg mr-2"
            value={newStudent.studentNumber}
            onChange={(e) =>
              setNewStudent({ ...newStudent, studentNumber: e.target.value })
            }
          />
          <input
            type="text"
            placeholder="School Id"
            className="px-2 py-1 border rounded-lg mr-2"
            value={newStudent.schoolId}
            onChange={(e) =>
              setNewStudent({ ...newStudent, schoolId: e.target.value })
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

export default Student;
