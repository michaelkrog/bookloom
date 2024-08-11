export default function DataListItem({ suffix, children }) {

    return (
        <li className="relative flex justify-between gap-x-6 px-4 py-5 hover:bg-gray-50 sm:px-6">
            <div className="flex min-w-0 gap-x-4">
                {children}
            </div>
            {!!suffix &&
                <div className="flex shrink-0 items-center gap-x-4">

                    {suffix}
                </div>
            }
        </li>
    )
}