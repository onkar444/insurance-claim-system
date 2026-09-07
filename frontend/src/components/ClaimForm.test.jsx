import {
    fireEvent,
    render,
    screen,
} from "@testing-library/react";

import { expect, test } from "vitest";

import { BrowserRouter } from "react-router-dom";

import { ClaimForm } from "./ClaimForm";


test("shows error when description is too short", () => {
    render(
        <BrowserRouter>
            <ClaimForm />
        </BrowserRouter>
    );

    // Enter description
    fireEvent.change(
        screen.getByLabelText(/description/i),
        {
            target: {
                value: "abc",
            },
        }
    );

    // Enter amount
    fireEvent.change(
        screen.getByLabelText(/amount/i),
        {
            target: {
                value: "1000",
            },
        }
    );

    // Submit form
    fireEvent.click(
        screen.getByRole("button", {
            name: /submit/i,
        })
    );

    // Check validation error
    expect(
        screen.getByText(
            /Description must be more than 5 characters/i
        )
    ).toBeInTheDocument();
});
