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
