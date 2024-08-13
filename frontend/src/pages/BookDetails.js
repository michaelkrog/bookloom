
import { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom';
import bookResource from '../resources/BookResource';

/**
 * BookDetails Component
 * 
 * This component displays detailed information about a single book. It fetches book details based on the book ID from the URL parameters and displays the book cover, title, price, and description.
 * 
 * Features:
 * - Fetches book data from `bookResource` using the `id` parameter from the URL.
 * - Displays the book's cover image, title, price, and description.
 * - Includes buttons for adding the book to a shopping bag (not yet implemented) and an empty button for future actions.
 * - Manages component state for loading, data, and error handling using React hooks (`useState` and `useEffect`).
 * 
 * Usage:
 * ```jsx
 * import BookDetails from './BookDetails';
 * 
 * function App() {
 *   return (
 *     <div>
 *       <BookDetails />
 *     </div>
 *   );
 * }
 * ```
 * 
 * @component
 * @example
 * // BookDetails component fetches and displays detailed information about a book
 * <BookDetails />
 */
export default function BookDetails() {
    const { id } = useParams();

    const [data, setData] = useState([]);
    const [isLoading, setIsLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const result = await bookResource.findById(id);
                setData(result);
                setIsLoading(false);
            } catch (err) {
                setError(err);
                setIsLoading(false);
            }
        };

        fetchData();
    }, []);

    return (
        <div className="bg-white">
            <div className="mx-auto max-w-2xl px-4 py-16 sm:px-6 sm:py-24 lg:max-w-7xl lg:px-8">
                <div className="lg:grid lg:grid-cols-2 lg:items-start lg:gap-x-8">
                    <div className="aspect-h-1 aspect-w-1 w-full">

                        <div>
                            <img
                                alt="Book cover"
                                src={'data:image/png;base64,' + data.imageData}
                                className="h-full w-full object-cover object-center sm:rounded-lg"
                            />
                        </div>

                    </div>

                    {/* Product info */}
                    <div className="mt-10 px-4 sm:mt-16 sm:px-0 lg:mt-0">
                        <h1 className="text-3xl font-bold tracking-tight text-gray-900">{data.title}</h1>

                        <div className="mt-3">
                            <h2 className="sr-only">Product information</h2>
                            <p className="text-3xl tracking-tight text-gray-900">{(data.price / 100).toLocaleString("en-US", {style:"currency", currency:"USD"})}</p>
                        </div>


                        <div className="mt-6">
                            <h3 className="sr-only">Description</h3>

                            <div
                                className="space-y-6 text-base text-gray-700"
                            >{data.description}</div>
                        </div>

                        <div className="mt-6">

                            <div className="mt-10 flex">
                                <button
                                    onClick={() => alert('Not implemented')}
                                    className="flex max-w-xs flex-1 items-center justify-center rounded-md border border-transparent bg-indigo-600 px-8 py-3 text-base font-medium text-white hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2 focus:ring-offset-gray-50 sm:w-full"
                                >
                                    Add to bag
                                </button>

                                <button
                                    type="button"
                                    className="ml-4 flex items-center justify-center rounded-md px-3 py-3 text-gray-400 hover:bg-gray-100 hover:text-gray-500"
                                >
                                </button>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    )
}
