import { observer } from "mobx-react";
import authStore from "../stores/AuthStore";

/**
 * MobileMenuLinks Component
 * 
 * This component renders different sets of navigation links in a mobile menu, depending on the user's role.
 * The user's role is determined by the `authStore`, which is observed using `mobx-react` to reactively update the component.
 * 
 * The component displays:
 * - **Admin Interface**: If the user has the `ROLE_ADMIN` role, it shows a link to the admin interface.
 * - **User Information**: If the user has the `ROLE_USER` role, it shows only the username.
 * - **Sign Up / Sign In Links**: If the user is not authenticated (i.e., no recognized role), it shows links for creating an account or signing in.
 * 
 * Props:
 * - None. The component relies on the `authStore` for determining the content to render.
 * 
 * Usage:
 * ```jsx
 * import MobileMenuLinks from './MobileMenuLinks';
 * 
 * function App() {
 *   return (
 *     <div className="mobile-menu">
 *       <MobileMenuLinks />
 *     </div>
 *   );
 * }
 * ```
 * 
 * @component
 * @example
 * <MobileMenuLinks />
 */
const MobileMenuLinks = observer(() => {
    
    
    if (authStore.roles.includes('ROLE_ADMIN')) {
        return (
            <div className="space-y-6 border-t border-gray-200 px-4 py-6">
                <div className="flow-root">
                    <label className="-m-2 block p-2 font-medium text-gray-900">
                        {authStore.username}
                    </label>
                </div>
                <div className="flow-root">
                    <a href="/admin/users" className="-m-2 block p-2 font-medium text-gray-900">
                        Admin interface
                    </a>
                </div>
            </div>
        );
    } else if (authStore.roles.includes('ROLE_USER')) {
        return (
            <div className="space-y-6 border-t border-gray-200 px-4 py-6">
                <div className="flow-root">
                    <label className="-m-2 block p-2 font-medium text-gray-900">
                    {authStore.username}
                    </label>
                </div>
            </div>
        );
    } else {
        return (

            <div className="space-y-6 border-t border-gray-200 px-4 py-6">
                <div className="flow-root">
                    <a href="/signup" className="-m-2 block p-2 font-medium text-gray-900">
                        Create an account
                    </a>
                </div>
                <div className="flow-root">
                    <a href="/signin" className="-m-2 block p-2 font-medium text-gray-900">
                        Sign in
                    </a>
                </div>
            </div>
        );
    }
});

export default MobileMenuLinks;