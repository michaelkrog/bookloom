import React, { Fragment, useEffect, useState } from 'react';
import userResource from '../../resources/UserResource'; 

import { ChevronRightIcon } from '@heroicons/react/20/solid'
import { Link } from 'react-router-dom';
import DataList from '../../components/DataList';
import DataListItem from '../../components/DataListItem';

/**
 * UserList Component
 * 
 * This component displays a list of users fetched from a data source. Each user entry includes their profile picture, name, and email address.
 * 
 * Features:
 * - Fetches user data from the `userResource` and displays it in a list.
 * - Shows a loading message while data is being fetched.
 * - Displays an error message if data fetching fails.
 * - Uses the `DataList` and `DataListItem` components to render the user list with a right-chevron icon indicating more details.
 * 

 * Usage:
 * ```jsx
 * import UserList from './UserList';
 * 
 * function App() {
 *   return (
 *     <div>
 *       <UserList />
 *     </div>
 *   );
 * }
 * ```
 * 
 * @component
 * @example
 * // UserList component fetches and displays a list of users
 * <UserList />
 */
const UserList = () => {
    const [data, setData] = useState([]);
    const [isLoading, setIsLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const result = await userResource.findAll();
                setData(result);
                setIsLoading(false);
            } catch (err) {
                setError(err);
                setIsLoading(false);
            }
        };

        fetchData();
    }, []);

    if (isLoading) return <p className="text-blue-500">Loading...</p>;
    if (error) return <p className="text-red-500">Error: {error.message}</p>;

    return (

        <DataList>
            
            {data.map((user) => (
                <DataListItem key={user.id} suffix={<ChevronRightIcon aria-hidden="true" className="h-5 w-5 flex-none text-gray-400" />}>
                    <Fragment>
                        <img alt="" src={user.imageUrl ?? '/avatar.avif'} className="h-12 w-12 flex-none rounded-full bg-gray-50" />
                        <div className="min-w-0 flex-auto">
                            <p className="text-sm font-semibold leading-6 text-gray-900">
                                <Link to={user.id}>
                                    <span className="absolute inset-x-0 -top-px bottom-0" />
                                    {user.name}
                                </Link>
                            </p>
                            <p className="mt-1 flex text-xs leading-5 text-gray-500">
                                <a href={`mailto:${user.email}`} className="relative truncate hover:underline">
                                    {user.email}
                                </a>
                            </p>
                        </div>
                    </Fragment>
                </DataListItem>

            ))}

        </DataList>
    );
};

export default UserList;

