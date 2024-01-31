const fetchData = async () => {
            try {
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
