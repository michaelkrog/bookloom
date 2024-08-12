import React, { Fragment, useEffect, useState } from 'react';
import userResource from '../../resources/UserResource'; 

import { ChevronRightIcon } from '@heroicons/react/20/solid'
import { Link } from 'react-router-dom';
import DataList from '../../components/DataList';
import DataListItem from '../../components/DataListItem';

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
                        <img alt="" src={user.imageUrl ?? 'https://images.unsplash.com/photo-1472099645785-5658abf4ff4e?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=facearea&facepad=2&w=256&h=256&q=80'} className="h-12 w-12 flex-none rounded-full bg-gray-50" />
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

