export function Claim({ claim, onDelete }) {

    async function handleDelete() {

        try {
            const res = await fetch(`http://localhost:8080/api/claim/delete/${claim.id}`, {
                method: "DELETE",
                headers: {
                    'Content-Type': 'application/json'
                }
            });

            if (!res.ok) {
                throw new Error(`Error while deleting claim: ${res.status}`);
            }

            //notify parent on removal
            onDelete(claim.id);
        }
        catch (er) {
            alert(er);
        }

    }

    return (
        <div style={{
            border: "1px solid black",
            padding: "10px",
            fontSize: "15px",
            fontFamily: "monospace",
            margin: "10px"
        }}>
            Id:{claim.id} <br />
            userId:{claim.userId} <br />
            policyId: {claim.policyId} <br />
            description: {claim.description} <br />
            amount: {claim.amount}<br />
            createdAt: {claim.createdAt}<br />
            updatedAt: {claim.updatedAt}<br />
            status: {claim.status}<br />
            <button onClick={handleDelete}>Delete</button>
        </div >
    )
}