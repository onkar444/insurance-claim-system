const BASE_URL = "http://localhost:8080/api";

export async function apiFetch(path, options = {}) {
    const token = localStorage.getItem("token");
    const headers = {
        ...options.headers
    };

    if (token) {
        headers.Authorization = `Bearer ${token}`;
    }

    console.log("Calling:", `${BASE_URL}${path}`);
    const response = await fetch(`${BASE_URL}${path}`, {
        ...options,
        headers
    })

    if (!response.ok) {
        const error = new Error(`Request failed:${response.status}`);
        error.status = response.status;
        if (response.status === 401) {
            localStorage.removeItem("token");
        }
        throw error;
    }

    return response;

}

export async function getPolicies() {
    const response = await apiFetch("/policies");
    return response.json();
}

export async function getUserMe() {
    const response = await apiFetch("/user/me");
    return response.json();
}

export async function getUsersList() {
    const response = await apiFetch("/user/all");
    return response.json();
}


export async function getClaims(id) {
    const response = await apiFetch(`/policy/${id}/claims`);
    return response.json();
}

export async function perfromLogin(email, password) {
    const response = await apiFetch("/auth/login", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({ email, password })
    });
    return response.text();
}

export async function registerUser(inputData) {

    const response = await apiFetch("/auth/register", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(inputData)
    });

    return response.text();
}

export async function deleteClaimById(id) {
    const response = await apiFetch(`/policy/${id}/claims`, {
        method: "DELETE",
        headers: {
            "Content-Type": "application/json"
        }
    });
    return response.json();
}

export async function saveClaim(claim) {
    const response = await apiFetch("/claim/save", {
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(claim)
    });
    return response.json();
}

export async function getPolicyById(id) {
    const response = await apiFetch(`/policy/${id}`);
    console.log("/policy/:id", response);
    return response.json();
}

export async function updatePolicy(policy) {
    const response = await apiFetch(`/policy/update/${policy.id}`, {
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(policy)
    });
    return response.json();
}