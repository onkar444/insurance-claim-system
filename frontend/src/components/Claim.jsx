import { deleteClaimById } from "../api/api";

export function Claim({ claim, onDelete }) {

    async function handleDelete() {

        try {
            deleteClaimById(claim.id);
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
            customerId:{claim.customerId} <br />
            adjusterId:{claim.adjusterId} <br />
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