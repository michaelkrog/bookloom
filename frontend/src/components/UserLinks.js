import { observer } from "mobx-react";
import authStore from "../stores/AuthStore";
import { Menu, MenuButton, MenuItem, MenuItems } from "@headlessui/react";
import { ArrowLeftStartOnRectangleIcon } from "@heroicons/react/20/solid";
import { ChevronDownIcon } from "@heroicons/react/20/solid";

const UserLinks = observer(() => {

    function signOut() {
        authStore.logout();
    }

    function renderUserMenu() {
        return <Menu as="div" className="relative inline-block text-left">
            <div>
                <MenuButton className="group inline-flex justify-center text-sm font-medium text-white hover:text-gray-100">
                    {authStore.username}
                    <ChevronDownIcon
                        aria-hidden="true"
                        className="-mr-1 ml-1 h-5 w-5 flex-shrink-0 text-gray-400 group-hover:text-gray-500"
                    />
                </MenuButton>
            </div>

            <MenuItems
                transition
                className="absolute left-0 z-10 mt-2 w-48 origin-top-left rounded-md bg-white shadow-2xl ring-1 ring-black ring-opacity-5 transition focus:outline-none data-[closed]:scale-95 data-[closed]:transform data-[closed]:opacity-0 data-[enter]:duration-100 data-[leave]:duration-75 data-[enter]:ease-out data-[leave]:ease-in"
            >
                <div className="py-1">
                    <MenuItem>
                        <a onClick={signOut}
                            className="group flex w-full items-center gap-2 px-4 py-2 text-sm font-medium text-gray-900 data-[focus]:bg-gray-100"
                        >
                            <ArrowLeftStartOnRectangleIcon CheckIcon className="size-4 fill-black/30" />
                            Sign out
                        </a>
                    </MenuItem>
                </div>
            </MenuItems>
        </Menu>
    }

    if (authStore.roles.includes('ROLE_ADMIN')) {
        return (
            <div className="flex items-center space-x-6">
                {renderUserMenu()}
                <a href="/admin/users" className="text-sm font-medium text-white hover:text-gray-100">
                    Admin interface
                </a>
            </div>
        );
    } else if (authStore.roles.includes('ROLE_USER')) {
        return (
            <div className="flex items-center space-x-6">
                {renderUserMenu()}
            </div>
        );
    } else {
        return (

            <div className="flex items-center space-x-6">
                <a href="/signin" className="text-sm font-medium text-white hover:text-gray-100">
                    Sign in
                </a>
                <a href="#" className="text-sm font-medium text-white hover:text-gray-100">
                    Create an account
                </a>
            </div>
        );
    }
});

export default UserLinks;