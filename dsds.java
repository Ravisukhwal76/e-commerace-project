        const dateObject = formData.date instanceof Date ? formData.date : new Date(formData.date);
        const dateString = dateObject.toISOString().split('T')[0];

        const formDataString = {
            ...formData,
            date: dateString,
        };

