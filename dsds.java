 const loginAction = async (data) => {
        const myHeaders = new Headers();
        myHeaders.append('Content-Type', 'application/json');

        const requestOptions = {
            method: 'POST',
            headers: myHeaders,
            body: JSON.stringify(data),
            credentials: 'include',
            redirect: 'follow',
        };

        try {
            const response = await fetch("http://localhost:8080/api/auth/login", requestOptions);
            const res = await response.json();  // Assuming the response is in JSON format

            // Check if the login was successful
            if (response.ok) {
                console.log(res);

                // Access and handle cookies
                const setCookieHeader = response.headers.get('Set-Cookie');
                if (setCookieHeader) {
                    // Parse the setCookieHeader if needed
                    console.log('Received cookies:', setCookieHeader);
                }

                setUser({ token: res.jwtToken });
                console.log(res.jwtToken);
                localStorage.setItem("authToken", res.jwtToken);
                localStorage.setItem("userId", res.id);
                navigate("/home");
            } else {
                // Failed login, handle the error
                const errorMessage = res.message || 'Login failed';
                alert(errorMessage);
                console.log(data);
            }
        } catch (err) {
            console.error(err);
        }
    };
