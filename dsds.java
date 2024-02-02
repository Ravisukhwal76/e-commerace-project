 @GetMapping("myTasks/progress/{id}")
   private ResponseEntity<Integer> calculateCompletionPercentage(@PathVariable("id") int id) {
	   List<TaskAssign> allTasks = taskAssignService.getAllTaskAssignByUserId(id);
	   // Include tasks where isStatus is true and isComplete is false
       List<TaskAssign> filteredTasks = allTasks.stream()
               .filter(taskAssign -> taskAssign.isStatus())
               .collect(Collectors.toList());
 
       long completedTasks = filteredTasks.stream().filter(TaskAssign::isComplted).count();
       double completionPercentage = ((double) completedTasks / allTasks.size()) * 100; 
	 
	   if (allTasks.isEmpty()) {
	        return  new ResponseEntity<>((int)completionPercentage, HttpStatus.OK); // No tasks, completion percentage is 0
	    }

	    
	      return new ResponseEntity<>((int)completionPercentage, HttpStatus.OK);
	}

///fronted 

import  { useState, useEffect } from 'react';
import './css/Notification.css';
import axios from "axios";

const MyTask = () => {
    const [tasks, setTasks] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [progress, setProgress] = useState(0);
    let value;
    const fetchData = async () => {
        try {
            const id = localStorage.getItem("userId");
            const authToken = localStorage.getItem('authToken');

            if (!authToken) {
                console.error('No authentication token found.');
                // Handle the absence of the token, e.g., redirect to login.
                return;
            }

            const axiosInstance = axios.create({
                baseURL: 'http://localhost:8080',
                withCredentials: true, // Include cookies with the request
            });

            const response = await axiosInstance.get(`/api/myTasks/${id}`, {
                headers: {
                    'Authorization': `Bearer ${authToken}`,
                    'Content-Type': 'application/json',
                },
            });

            const response1 = await axiosInstance.get(`/api/myTasks/progress/${id}`, {
                headers: {
                    'Authorization': `Bearer ${authToken}`,
                    'Content-Type': 'application/json',
                },
            });
            setProgress(response1.data)

            console.log('Data received:', response.data);
            setTasks(Array.isArray(response.data) ? response.data : []);
            setLoading(false);
        } catch (error) {
            console.error('Error fetching data:', error);
            setError('Error fetching data. Please try again.');
            setLoading(false);
        }
    };

    useEffect(() => {
        fetchData();
    }, []); // The empty dependency array ensures that this effect runs only once, similar to componentDidMount

    const handleCompleteTask = async (taskId) => {
        try {
            const authToken = localStorage.getItem('authToken');

            if (!authToken) {
                console.error('No authentication token found.');
                return;
            }

            const axiosInstance = axios.create({
                baseURL: 'http://localhost:8080',
                withCredentials: true,
            });

            await axiosInstance.patch(`/api/task/${taskId}/complete`, null, {
                headers: {
                    'Content-Type': 'application/json',
                },
                withCredentials: true,
            });

            // Call the fetchData function after completing the task
            fetchData();

        } catch (error) {
            console.error('Error completing task:', error);
            console.log('Response:', error.response);  // Log the full response for more details
        }
    };

    return (
        <div className="ds-container">
                <div className="flex">
                    {loading ? (
                        <p>Loading...</p>
                    ) : error ? (
                        <p>{error}</p>
                    ) : (
                        tasks.map(task => (
                            <div key={task.id} className="task-card">
                                <div className="task-info">
                                    <small className="title"><b>{task.title}</b></small>
                                    <p>{task.description}</p>
                                </div>
                                <div className="cta display">
                                    <p>{`Assigned by: ${task.addedBy}`}</p>
                                    <div>
                                        <form>
                                            <input type="hidden" name="id" value={task.id}/>
                                            <button type="button" onClick={() => handleCompleteTask(task.id)}>
                                                Completed
                                            </button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        ))
                    )}
                </div>
            <h1>Progress Bar</h1>
            <div style={{ width: '300px', border: '1px solid #ccc', borderRadius: '4px', overflow: 'hidden' }}>
                <div
                    style={{
                        width: `${progress}%`,
                        height: '30px',
                        backgroundColor: '#4CAF50',
                        textAlign: 'center',
                        lineHeight: '30px',
                        color: 'white',
                    }}
                >
                    {`${ progress}%`}
                </div>
            </div>
            </div>

    );
};

export default MyTask;
