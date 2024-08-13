'use client'

import { useState } from 'react'
import { Dialog, DialogBackdrop, DialogPanel, TransitionChild } from '@headlessui/react'
import {
  Bars3Icon,
  BookOpenIcon,
  DocumentTextIcon,
  UsersIcon,
  XMarkIcon,
} from '@heroicons/react/24/outline'
import { Link, matchPath, Outlet, BrowserRouter as Router, useLocation } from 'react-router-dom'
import authStore from '../stores/AuthStore'

const navigation = [
  { name: 'Users', href: 'users', icon: UsersIcon, current: true },
  { name: 'Books', href: 'books', icon: BookOpenIcon, current: false },
  { name: 'Orders', href: 'orders', icon: DocumentTextIcon, current: false },
]

function classNames(...classes) {
  return classes.filter(Boolean).join(' ')
}

/**
 * AdminShell Component
 * 
 * The `AdminShell` component serves as the main layout for the admin interface. It includes a sidebar with navigation links and a top bar for mobile views. The sidebar and top bar are designed to help users navigate through different sections of the admin panel. The sidebar includes links for Users, Books, and Orders, and shows a profile section with the user's avatar and name.
 * 
 * Features:
 * - Responsive sidebar that can be toggled open and closed on mobile devices.
 * - Static sidebar for larger screens.
 * - Dynamic highlighting of the current active route.
 * - Top bar for mobile devices that includes a menu button to open the sidebar and displays the user profile.
 * 
 * @component
 * @param {Object} props - Component props.
 * @param {React.ReactNode} props.children - The content to be rendered inside the main section of the layout.
 * 
 * @example
 * import AdminShell from './AdminShell';
 * 
 * function AdminPage() {
 *   return (
 *     <AdminShell>
 *       {/ Page content goes here /}
 *     </AdminShell>
 *   );
 * }
 * 
 * @returns {JSX.Element} The rendered AdminShell component.
 */
export default function AdminShell({ children }) {
  const [sidebarOpen, setSidebarOpen] = useState(false)
  const location = useLocation();

  function isActive(item) {
    return matchPath({ path: `/admin/${item.href}`}, location.pathname);
  }

  return (

      <div>
        <Dialog open={sidebarOpen} onClose={setSidebarOpen} className="relative z-50 lg:hidden">
          <DialogBackdrop
            transition
            className="fixed inset-0 bg-gray-900/80 transition-opacity duration-300 ease-linear data-[closed]:opacity-0"
          />

          <div className="fixed inset-0 flex">
            <DialogPanel
              transition
              className="relative mr-16 flex w-full max-w-xs flex-1 transform transition duration-300 ease-in-out data-[closed]:-translate-x-full"
            >
              <TransitionChild>
                <div className="absolute left-full top-0 flex w-16 justify-center pt-5 duration-300 ease-in-out data-[closed]:opacity-0">
                  <button type="button" onClick={() => setSidebarOpen(false)} className="-m-2.5 p-2.5">
                    <span className="sr-only">Close sidebar</span>
                    <XMarkIcon aria-hidden="true" className="h-6 w-6 text-white" />
                  </button>
                </div>
              </TransitionChild>
              {/* Sidebar component, swap this element with another sidebar if you like */}
              <div className="flex grow flex-col gap-y-5 overflow-y-auto bg-white px-6 pb-2">
                <div className="flex h-16 shrink-0 items-center">
                  
                  Bookloom admin
                </div>
                <nav className="flex flex-1 flex-col">
                  <ul className="flex flex-1 flex-col gap-y-7">
                    <li>
                      <ul className="-mx-2 space-y-1">
                        {navigation.map((item) => (
                          <li key={item.name}>
                            <a
                              href={item.href}
                              className={classNames(
                                isActive(item)
                                  ? 'bg-gray-50 text-indigo-600'
                                  : 'text-gray-700 hover:bg-gray-50 hover:text-indigo-600',
                                'group flex gap-x-3 rounded-md p-2 text-sm font-semibold leading-6',
                              )}
                            >
                              <item.icon
                                aria-hidden="true"
                                className={classNames(
                                  isActive(item) ? 'text-indigo-600' : 'text-gray-400 group-hover:text-indigo-600',
                                  'h-6 w-6 shrink-0',
                                )}
                              />
                              {item.name}
                            </a>
                          </li>
                        ))}
                      </ul>
                    </li>
                    
                  </ul>
                </nav>
              </div>
            </DialogPanel>
          </div>
        </Dialog>

        {/* Static sidebar for desktop */}
        <div className="hidden lg:fixed lg:inset-y-0 lg:z-50 lg:flex lg:w-72 lg:flex-col">
          {/* Sidebar component, swap this element with another sidebar if you like */}
          <div className="flex grow flex-col gap-y-5 overflow-y-auto border-r border-gray-200 bg-white px-6">
            <div className="flex h-16 shrink-0 items-center">

              Bookloom admin
            </div>
            <nav className="flex flex-1 flex-col">
              <ul className="flex flex-1 flex-col gap-y-7">
                <li>
                  <ul className="-mx-2 space-y-1">
                    {navigation.map((item) => (
                      <li key={item.name}>
                        <Link
                          to={item.href}
                          className={classNames(
                            isActive(item)
                              ? 'bg-gray-50 text-indigo-600'
                              : 'text-gray-700 hover:bg-gray-50 hover:text-indigo-600',
                            'group flex gap-x-3 rounded-md p-2 text-sm font-semibold leading-6',
                          )}
                        >
                          <item.icon
                            aria-hidden="true"
                            className={classNames(
                              isActive(item) ? 'text-indigo-600' : 'text-gray-400 group-hover:text-indigo-600',
                              'h-6 w-6 shrink-0',
                            )}
                          />
                          {item.name}
                        </Link>
                      </li>
                    ))}
                  </ul>
                </li>
                
                <li className="-mx-6 mt-auto">
                  <a
                    href="#"
                    className="flex items-center gap-x-4 px-6 py-3 text-sm font-semibold leading-6 text-gray-900 hover:bg-gray-50"
                  >
                    <img
                      alt=""
                      src="/avatar.avif"
                      className="h-8 w-8 rounded-full bg-gray-50"
                    />
                    <span className="sr-only">Your profile</span>
                    <span aria-hidden="true">{authStore.username}</span>
                  </a>
                </li>
              </ul>
            </nav>
          </div>
        </div>

        <div className="sticky top-0 z-40 flex items-center gap-x-6 bg-white px-4 py-4 shadow-sm sm:px-6 lg:hidden">
          <button type="button" onClick={() => setSidebarOpen(true)} className="-m-2.5 p-2.5 text-gray-700 lg:hidden">
            <span className="sr-only">Open sidebar</span>
            <Bars3Icon aria-hidden="true" className="h-6 w-6" />
          </button>
          <div className="flex-1 text-sm font-semibold leading-6 text-gray-900">Dashboard</div>
          <a href="#">
            <span className="sr-only">Your profile</span>
            <img
              alt=""
              src="/avatar.avif"
              className="h-8 w-8 rounded-full bg-gray-50"
            />
          </a>
        </div>

        <main className="py-10 lg:pl-72">
          <div className="px-4 sm:px-6 lg:px-8">
            <Outlet />
          </div>
        </main>
      </div>

  )
}
