import { useState } from "react"
import userResource from '../resources/UserResource';
import { useNavigate } from "react-router-dom";

/**
 * SignIn Component
 * 
 * This component provides a sign-in form where users can authenticate themselves by providing their email and password. It uses the `userResource` for authentication and handles navigation upon successful sign-in using the `useNavigate` hook from `react-router-dom`.
 * 
 * Features:
 * - User input fields for email and password.
 * - A button to trigger the sign-in process.
 * - Navigation to the home page upon successful authentication.
 * - Error handling with an alert message if authentication fails.
 * 
 * Usage:
 * ```jsx
 * import SignIn from './SignIn';
 * 
 * function App() {
 *   return (
 *     <div>
 *       <SignIn />
 *     </div>
 *   );
 * }
 * ```
 * 
 * @component
 * @example
 * // SignIn component displays a form for user authentication
 * <SignIn />
 */
export default function SignIn() {
    const [email, setEmail ] = useState();
    const [password, setPassword ] = useState();
    const navigate = useNavigate();

    // Handlers to update state on input change
    function handleEmailChange(event) {
        setEmail(event.target.value);
    };

    function handlePasswordChange(event) {
        setPassword(event.target.value);
    };

    async function signin() {
        try {
            await userResource.authenticate(email, password);

            // Succeeded
            navigate('/');
        } catch (error) {
            alert(error.detail);
        }
    }

    return (
        <>
            <div className="flex min-h-full flex-1 flex-col justify-center px-6 py-12 lg:px-8">
                <div className="sm:mx-auto sm:w-full sm:max-w-sm">
                    <h2 className="mt-10 text-center text-2xl font-bold leading-9 tracking-tight text-gray-900">
                        Sign in to your account
                    </h2>
                </div>

                <div className="mt-10 sm:mx-auto sm:w-full sm:max-w-sm">
                    <div className="space-y-6">
                        <div>
                            <label htmlFor="email" className="block text-sm font-medium leading-6 text-gray-900">
                                Email address
                            </label>
                            <div className="mt-2">
                                <input
                                    id="email"
                                    name="email"
                                    type="email"
                                    required
                                    autoComplete="email"
                                    onChange={handleEmailChange}
                                    className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
                                />
                            </div>
                        </div>

                        <div>
                            <div className="flex items-center justify-between">
                                <label htmlFor="password" className="block text-sm font-medium leading-6 text-gray-900">
                                    Password
                                </label>
                            </div>
                            <div className="mt-2">
                                <input
                                    id="password"
                                    name="password"
                                    type="password"
                                    required
                                    autoComplete="current-password"
                                    onChange={handlePasswordChange}
                                    className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
                                />
                            </div>
                        </div>

                        <div>
                            <button
                                onClick={signin}
                                type="submit"
                                className="flex w-full justify-center rounded-md bg-indigo-600 px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600"
                            >
                                Sign in
                            </button>
                        </div>
                    </div>

                    <p className="mt-10 text-center text-sm text-gray-500">
                        Not a member?{' '}
                        <a href="#" className="font-semibold leading-6 text-indigo-600 hover:text-indigo-500">
                            Create a user now
                        </a>
                    </p>
                </div>
            </div>
        </>
    )
}
