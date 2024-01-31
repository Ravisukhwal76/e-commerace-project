    const fetchData = async () => {
            try {
                const axiosInstance = axios.create({
                    baseURL: 'http://localhost:8080',
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': `Bearer ${authToken}`, // Use Authorization header for token
                    },
                    withCredentials: true,
                });

                const response = await axiosInstance.get(`/api/myTask/${id}`);
                setTasks(response.data);
            } catch (error) {
                console.error('Error fetching data:', error);
                console.log('Response:', error.response); // Log the response for more details
                setError('Error fetching data. Please try again.');
            } finally {
                setLoading(false);
            }
        };

        fetchData();
    }, []);
