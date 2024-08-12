import React, { Fragment, useEffect, useState } from 'react';
import bookResource from '../../resources/BookResource'; 

import { ChevronRightIcon } from '@heroicons/react/20/solid'
import { Link } from 'react-router-dom';
import DataList from '../../components/DataList';
import DataListItem from '../../components/DataListItem';

const BookList = () => {
    const [data, setData] = useState([]);
    const [isLoading, setIsLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const result = await bookResource.findAll();
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
            {data.map((book) => (
                <DataListItem key={book.id} suffix={<ChevronRightIcon aria-hidden="true" className="h-5 w-5 flex-none text-gray-400" />}>
                    <Fragment>
                        <img alt="" src={'data:image/png;base64,' + book.imageData} className="h-12 w-12 flex-none object-cover object-center sm:rounded-lg border-2 bg-gray-50" />
                        <div className="min-w-0 flex-auto">
                            <p className="text-sm font-semibold leading-6 text-gray-900">
                                <Link to={book.id}>
                                    <span className="absolute inset-x-0 -top-px bottom-0" />
                                    {book.title}
                                </Link>
                            </p>
                            <p className="mt-1 flex text-xs leading-5 text-gray-500">
                                <a href={`mailto:${book.email}`} className="relative truncate hover:underline">
                                    {book.author}
                                </a>
                            </p>
                        </div>
                    </Fragment>
                </DataListItem>

            ))}

        </DataList>
    );
};

export default BookList;

