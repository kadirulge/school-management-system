import React, { useState, useEffect } from "react";

function School() {
  const [schools, setSchools] = useState([]);
  const [editMode, setEditMode] = useState(false);
  const [editedSchool, setEditedSchool] = useState({});
  const [newSchool, setNewSchool] = useState({
    name: "",
    address: "",
    dateOfEstablishment: "",
    numberOfClasses: "",
  });

  useEffect(() => {
    fetchSchools();
  }, [editMode]);

  const fetchSchools = async () => {
    try {
      const response = await fetch("/api/schools");
      const data = await response.json();
      setSchools(data);
    } catch (error) {
      console.error("Error:", error);
    }
  };

  const handleEdit = (school) => {
    setEditMode(true);
    setEditedSchool(school);
  };

  const handleCancel = () => {
    setEditMode(false);
    setEditedSchool({});
  };

  const handleSave = async () => {
    try {
      const response = await fetch(`/api/schools/${editedSchool.id}`, {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(editedSchool),
      });

      if (response.ok) {
        console.log("Updated:", editedSchool);
        setEditMode(false);
        setEditedSchool({});
      } else {
        console.error("Update failed");
      }
    } catch (error) {
      console.error("Error:", error);
    }
  };

  const handleDelete = async (id) => {
    try {
      const response = await fetch(`/api/schools/${id}`, {
        method: "DELETE",
      });

      if (response.ok) {
        console.log("Deleted:", id);
        fetchSchools();
      } else {
        console.error("Delete failed");
      }
    } catch (error) {
      console.error("Error:", error);
    }
  };

  const handleCreate = async () => {
    try {
      const response = await fetch("/api/schools", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(newSchool),
      });

      if (response.ok) {
        const createdSchool = await response.json();
        setSchools([...schools, createdSchool]);
        console.log("Created:", createdSchool);
        setNewSchool({
          name: "",
          address: "",
          dateOfEstablishment: "",
          numberOfClasses: "",
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
                    School Id
                  </th>
                  <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                    School Name
                  </th>
                  <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                    School Address
                  </th>
                  <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                    Date of Establishment
                  </th>
                  <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                    Number of Classes
                  </th>
                  <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                    Actions
                  </th>
                </tr>
              </thead>
              <tbody className="bg-white divide-y divide-gray-200">
                {schools.map((school) => (
                  <tr key={school.id}>
                    <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                      {school.id}
                    </td>
                    <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                      {editMode && editedSchool.id === school.id ? (
                        <input
                          type="text"
                          className="px-2 py-1 border rounded-lg"
                          value={editedSchool.name || ""}
                          onChange={(e) =>
                            setEditedSchool({
                              ...editedSchool,
                              name: e.target.value,
                            })
                          }
                        />
                      ) : (
                        school.name
                      )}
                    </td>
                    <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                      {editMode && editedSchool.id === school.id ? (
                        <input
                          type="text"
                          className="px-2 py-1 border rounded-lg"
                          value={editedSchool.address || ""}
                          onChange={(e) =>
                            setEditedSchool({
                              ...editedSchool,
                              address: e.target.value,
                            })
                          }
                        />
                      ) : (
                        school.address
                      )}
                    </td>
                    <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                      {editMode && editedSchool.id === school.id ? (
                        <input
                          type="text"
                          className="px-2 py-1 border rounded-lg"
                          value={editedSchool.dateOfEstablishment || ""}
                          onChange={(e) =>
                            setEditedSchool({
                              ...editedSchool,
                              dateOfEstablishment: e.target.value,
                            })
                          }
                        />
                      ) : (
                        school.dateOfEstablishment
                      )}
                    </td>
                    <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                      {editMode && editedSchool.id === school.id ? (
                        <input
                          type="text"
                          className="px-2 py-1 border rounded-lg"
                          value={editedSchool.numberOfClasses || ""}
                          onChange={(e) =>
                            setEditedSchool({
                              ...editedSchool,
                              numberOfClasses: e.target.value,
                            })
                          }
                        />
                      ) : (
                        school.numberOfClasses
                      )}
                    </td>
                    <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                      {editMode && editedSchool.id === school.id ? (
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
                          onClick={() => handleEdit(school)}
                        >
                          Edit
                        </button>
                      )}
                      {!editMode && (
                        <button
                          className="text-red-500 ms-3"
                          onClick={() => handleDelete(school.id)}
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
          Create New School
        </h2>
        <div className="flex">
          <input
            type="text"
            className="px-2 py-1 border rounded-lg mr-2"
            placeholder="School Name"
            value={newSchool.name}
            onChange={(e) =>
              setNewSchool({ ...newSchool, name: e.target.value })
            }
          />
          <input
            type="text"
            className="px-2 py-1 border rounded-lg mr-2"
            placeholder="School Address"
            value={newSchool.address}
            onChange={(e) =>
              setNewSchool({ ...newSchool, address: e.target.value })
            }
          />
          <input
            type="text"
            className="px-2 py-1 border rounded-lg mr-2"
            placeholder="Date of Establishment"
            value={newSchool.dateOfEstablishment}
            onChange={(e) =>
              setNewSchool({
                ...newSchool,
                dateOfEstablishment: e.target.value,
              })
            }
          />
          <input
            type="text"
            className="px-2 py-1 border rounded-lg mr-2"
            placeholder="Number of Classes"
            value={newSchool.numberOfClasses}
            onChange={(e) =>
              setNewSchool({ ...newSchool, numberOfClasses: e.target.value })
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

export default School;
