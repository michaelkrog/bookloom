import { observer } from "mobx-react";
import authStore from "../stores/AuthStore";

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
                    <a href="/admin" className="-m-2 block p-2 font-medium text-gray-900">
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