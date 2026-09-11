const BASE_URL = "http://localhost:8080/api";

export async function apiFetch(path, options = {}) {
    const token = localStorage.getItem("token");
    const headers = {
        ...options.headers
    };

    if (token) {
        headers.Authorization = `Bearer ${token}`;
    }

    const response = await fetch(`${BASE_URL}${path}`, {
        ...options,
        headers
    })

    if (!response.ok) {
        throw new Error(`Request failed:${response.status}`);
    }

}

export async function getPolicies() {
    const response = await apiFetch("/policies");
    return response.json();
}