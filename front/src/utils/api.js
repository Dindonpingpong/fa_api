const API_BASE_URL = process.env.REACT_APP_API_BASE_URL || 'http://localhost:8080/api';
const ACCESS_TOKEN = 'accessToken';

const request = (options) => {
    const headers = new Headers({
        'Content-Type': 'application/json',
    })

    if (localStorage.getItem(ACCESS_TOKEN)) {
        headers.append('Authorization', 'Bearer ' + localStorage.getItem(ACCESS_TOKEN))
    }

    const defaults = { headers: headers };
    options = Object.assign({}, defaults, options);

    return fetch(options.url, options)
        .then(response => {
            if (!response.ok) {
                return {
                    status: response.status,
                    data: null
                }
            }
            return response.json().then(json => {
                return {
                    status: response.status,
                    data: json
                }
            })
        });
};

export const loginFetch = (loginRequest) => {
    return request({
        url: API_BASE_URL + "/auth/signin",
        method: 'POST',
        body: JSON.stringify(loginRequest)
    });
}

export const signup = (signupRequest) => {
    return request({
        url: API_BASE_URL + "/auth/signup",
        method: 'POST',
        body: JSON.stringify(signupRequest)
    });
}

export const checkUsernameAvailability = (username) => {
    return request({
        url: API_BASE_URL + "/user/checkUsernameAvailability?username=" + username,
        method: 'GET'
    });
}

export const checkEmailAvailability = (email) => {
    return request({
        url: API_BASE_URL + "/user/checkEmailAvailability?email=" + email,
        method: 'GET'
    });
}

export const getCurrentUser = () => {
    if (!localStorage.getItem(ACCESS_TOKEN)) {
        return Promise.reject("No access token set.");
    }

    return request({
        url: API_BASE_URL + "/user/me",
        method: 'GET'
    });
}

export const getAllProducts = () => {
    return request({
        url: API_BASE_URL + "/product/all",
        method: 'GET'
    });
}

export const createProduct = (orderRequest) => {
    return request({
        url: API_BASE_URL + "/product",
        method: 'POST',
        body: JSON.stringify(orderRequest)
    });
}

export const getAllMenu = () => {
    return request({
        url: API_BASE_URL + "/menu/all",
        method: 'GET'
    });
}

export const deleteMenu = (id) => {
    return request({
        url: API_BASE_URL + "/menu/" + id,
        method: "DELETE"
    })
}

export const createMenu = (menuRequest) => {
    return request({
        url: API_BASE_URL + "/menu",
        method: 'POST',
        body: JSON.stringify(menuRequest)
    });
}

export const updateMenu = (id, menuRequest) => {
    return request({
        url: API_BASE_URL + "/menu/" + id,
        method: 'PUT',
        body: JSON.stringify(menuRequest)
    });
}

export const createOrder = (orderRequest) => {
    return request({
        url: API_BASE_URL + "/order",
        method: 'POST',
        body: JSON.stringify(orderRequest)
    });
}
