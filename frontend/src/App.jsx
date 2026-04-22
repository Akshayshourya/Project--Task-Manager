import { useState } from "react";

function App() {

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [tasks, setTasks] = useState([]);

  const handleLogin = async () => {
    const response = await fetch("http://localhost:8080/users/login", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({
        email: email,
        password: password
      })
    });

    const data = await response.json();
    localStorage.setItem("token",data.token);

    console.log(data);

    await getTasks();
  };


 const getTasks = async()=> {
    const token = localStorage.getItem("token");
    const response =  await fetch("http://localhost:8080/tasks",{ headers: { Authorization: `Bearer ${token}`} });

    const data = await response.json();
    setTasks(data.content);
    console.log(data.content);
  }


const handleMarkComplete = async (taskId) => {
  const token = localStorage.getItem("token");
  await fetch(`http://localhost:8080/tasks/${taskId}/complete`,{ method: "PATCH", headers: { Authorization: `Bearer ${token}`} });
  
  await getTasks();
}


  return (
    <div>

      <h1>Task Manager</h1>
      <h2>Login</h2>
      <br /><br />

      <h2>Tasks</h2>

        {
          tasks.map(task => (
            <div key={task.id}>
              <h3>{task.id}{task.title}</h3>
              <p>{task.description}</p>
              <p>Status : {task.completed? "Completed!":"Pending.."}</p>
              <button onClick={() => handleMarkComplete(task.id)}>Mark Complete!</button>
              <hr />
            </div>
          ))
        }

        <br /><br />

      <input
        type="email"
        placeholder="Enter email"
        onChange={(e) => setEmail(e.target.value)}
      />

      <br/><br/>

      <input
        type="password"
        placeholder="Enter password"
        onChange={(e) => setPassword(e.target.value)}
      />

      <br/><br/>

      <button onClick={handleLogin}>Login</button>

    </div>

  );

}

export default App;